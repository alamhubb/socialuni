import fs from 'node:fs';
import path from 'node:path';
import { createFilter as _createFilter } from '@rollup/pluginutils';
import { isWindows, slash } from '../shared/utils';
/**
 * Inlined to keep `@rollup/pluginutils` in devDependencies
 */
export type FilterPattern = ReadonlyArray<string | RegExp> | string | RegExp | null;
export const createFilter = _createFilter as (include?: FilterPattern, exclude?: FilterPattern, options?: {
    resolve?: string | false | null;
}) => (id: string | unknown) => boolean;

export function isInNodeModules(id: string): boolean {
    return id.includes('node_modules');
}

export function normalizePath(id: string): string {
    return path.posix.normalize(isWindows ? slash(id) : id);
}

function optimizeSafeRealPathSync() {
    // Skip if using Node <18.10 due to MAX_PATH issue: https://github.com/vitejs/vite/issues/12931
    const nodeVersion = process.versions.node.split('.').map(Number);
    if (nodeVersion[0] < 18 || (nodeVersion[0] === 18 && nodeVersion[1] < 10)) {
        safeRealpathSync = fs.realpathSync;
        return;
    }
    // Check the availability `fs.realpathSync.native`
    // in Windows virtual and RAM disks that bypass the Volume Mount Manager, in programs such as imDisk
    // get the error EISDIR: illegal operation on a directory
    try {
        fs.realpathSync.native(path.resolve('./'));
    }
    catch (error) {
        if (error.message.includes('EISDIR: illegal operation on a directory')) {
            safeRealpathSync = fs.realpathSync;
            return;
        }
    }
    exec('net use', (error, stdout) => {
        if (error)
            return;
        const lines = stdout.split('\n');
        // OK           Y:        \\NETWORKA\Foo         Microsoft Windows Network
        // OK           Z:        \\NETWORKA\Bar         Microsoft Windows Network
        for (const line of lines) {
            const m = parseNetUseRE.exec(line);
            if (m)
                windowsNetworkMap.set(m[2], m[1]);
        }
        if (windowsNetworkMap.size === 0) {
            safeRealpathSync = fs.realpathSync.native;
        }
        else {
            safeRealpathSync = windowsMappedRealpathSync;
        }
    });
}

// `fs.realpathSync.native` resolves differently in Windows network drive,
// causing file read errors. skip for now.
// https://github.com/nodejs/node/issues/37737
export let safeRealpathSync = isWindows
    ? windowsSafeRealPathSync
    : fs.realpathSync.native;

// Based on https://github.com/larrybahr/windows-network-drive
// MIT License, Copyright (c) 2017 Larry Bahr
const windowsNetworkMap = new Map();
function windowsMappedRealpathSync(path: string) {
    const realPath = fs.realpathSync.native(path);
    if (realPath.startsWith('\\\\')) {
        for (const [network, volume] of windowsNetworkMap) {
            if (realPath.startsWith(network))
                return realPath.replace(network, volume);
        }
    }
    return realPath;
}
let firstSafeRealPathSyncRun = false;
function windowsSafeRealPathSync(path: string): string {
    if (!firstSafeRealPathSyncRun) {
        optimizeSafeRealPathSync();
        firstSafeRealPathSyncRun = true;
    }
    return fs.realpathSync(path);
}

export function tryStatSync(file: string): fs.Stats | undefined {
    try {
        // The "throwIfNoEntry" is a performance optimization for cases where the file does not exist
        return fs.statSync(file, { throwIfNoEntry: false });
    }
    catch {
        // Ignore errors
    }
}
