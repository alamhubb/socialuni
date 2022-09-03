const fileRegex = /\.(cssjs)$/

export default function myPlugin() {
    return {
        name: 'vite:cssjs',
        transform(src, id) {
            console.log(id)
            if (fileRegex.test(id)) {
                console.log(src)

                const a = eval(src)
                console.log(a)
                return {
                    code: src,
                    map: null // 如果可行将提供 source map
                }
            }
        }
    }
}
