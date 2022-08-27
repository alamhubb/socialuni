import {h} from 'vue'
import Markdown from "@/components/Markdown.vue";

export default async function MarkdownVue(srcPromise: Promise<{ default: string }>) {
    const res = await srcPromise
    return h(Markdown, {
        src: res.default
    })
}
