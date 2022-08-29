var rollupPluginutils = require('rollup-pluginutils');

function markdown() {
    const filter = rollupPluginutils.createFilter(['**/*.md']);
    return {
        name: 'md',
        transform(md, id) {
            if (!filter(id)) {
                return null
            }
            return {
                code: `export default ${JSON.stringify(md)};`
            }
        }
    }
}

module.exports = markdown;
