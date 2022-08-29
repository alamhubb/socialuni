import {createFilter} from 'rollup-pluginutils'


export default function VitePluginMarkdown() {
    const filter = createFilter(['**/*.md']);
    return {
        name: 'markdown',
        transform(md, id) {
            if (!filter(id)) {
                return null
            }
            console.log(md)
            return {
                code: `export default ${JSON.stringify(md)};`
            }
        }
    }
}
