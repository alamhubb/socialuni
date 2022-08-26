import {createFilter} from 'rollup-pluginutils';

const mdString = () => {
    console.log(11111)
    const filter = createFilter(['**/*.md']);

    return {
        name: 'md',
        transform(md: string, id: string) {
            if (!filter(id)) return null;
            console.log(11111111)
            console.log(id)
            console.log(11111111)

            return {
                code: `export default ${JSON.stringify(md)};`
            };
        }
    };
}
export default mdString
