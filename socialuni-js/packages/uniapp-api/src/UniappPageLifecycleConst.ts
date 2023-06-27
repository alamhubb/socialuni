export default class UniappPageLifecycleConst {
    static readonly splitSymbol = '$##$'
    private static readonly onLoad = 'uniapp_onLoad'

    static getOnLoadKey($route) {
        return UniappPageLifecycleConst.onLoad + UniappPageLifecycleConst.splitSymbol + $route.path
    }
}
