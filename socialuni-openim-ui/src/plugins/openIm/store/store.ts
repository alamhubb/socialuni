import OpenImUserModule from "@/plugins/openIm/store/OpenImUserModule";

export let openImUserModule: OpenImUserModule

export function initOpenImStore() {
    openImUserModule = new OpenImUserModule()
}


