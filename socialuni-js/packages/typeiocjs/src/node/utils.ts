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

// `fs.realpathSync.native` resolves differently in Windows network drive,
// causing file read errors. skip for now.
// https://github.com/nodejs/node/issues/37737
export let safeRealpathSync = isWindows
    ? windowsSafeRealPathSync
    : fs.realpathSync.native;

export function tryStatSync(file: string): fs.Stats | undefined {
    try {
        // The "throwIfNoEntry" is a performance optimization for cases where the file does not exist
        return fs.statSync(file, { throwIfNoEntry: false });
    }
    catch {
        // Ignore errors
    }
}

const postfixRE = /[?#].*$/;
export function cleanUrl(url: string): string {
    return url.replace(postfixRE, '');
}
