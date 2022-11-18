## 快速启动
### 在当前目录 socialuni-js 下执行npm i
### 再在需要启动的项目下 执行 npm i
### 然后就npm run dev:h5可以启动项目了

## 关于为什么需要执行两次npm i？
使用了多包管理工具lerna，
同时为了保持目录整洁将依赖包放到了packages下面，
所以导致无法只执行一次npm i 就可以启动，
需要在根目录执行一次，再在子目录执行一次


## 配置文件修改

.env.development 开发环境配置
.env.production 生产环境配置
