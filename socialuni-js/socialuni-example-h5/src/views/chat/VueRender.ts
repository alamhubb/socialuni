import {h} from "vue";

export function div(...args: any[]) {
    return h('div', ...args)
}

export function p(...args: any[]) {
    return h('p', ...args)
}

export function a(...args: any[]) {
    return h('a', ...args)
}

export function img(...args: any[]) {
    return h('img', ...args)
}

export function input(...args: any[]) {
    return h('input', ...args)
}

export function audio(...args: any[]) {
    return h('audio', ...args)
}
