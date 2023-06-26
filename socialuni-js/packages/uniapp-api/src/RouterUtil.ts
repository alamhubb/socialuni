import {createRouter, createWebHistory} from "vue-router";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [],
})

export function getCurrentRoute(){
    return router.currentRoute
}
