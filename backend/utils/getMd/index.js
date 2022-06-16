const marked = require('marked')
const fs = require('fs')
//导入数据库操作模块
const db = require('../../db/index')
//导入path模块
const path = require('path')

//todo:路径优化，算法优化
//todo:按头部标签分类

//marked配置
const rendererMD = new marked.Renderer();
marked.setOptions({
    renderer: rendererMD,
    gfm: true, // 允许 Git Hub标准的markdown
    tables: true, // 允许支持表格语法
    breaks: true, // 允许回车换行
    pedantic: false, // 不纠正原始模型任何的不良行为和错误
    sanitize: false, // 输出进行过滤（清理）
    smartLists: true, // 使用比原生markdown更时髦的列表
    smartypants: true, // 使用更为时髦的标点，比如在引用语法中加入破折号
});

//读出目标文件夹下的每一个md文件
//读取指定文件的绝对地址，到时候改成服务器的绝对地址，或者去github上面同步下载
let dirName = 'D:\\学习笔记\\手写\\try\\all'
exports.transfrom = () => {
    return new Promise((resolve, reject) => {
        const files = fs.readdirSync(dirName)
        //创建文件夹
        // if (!fs.existsSync(dirName + '\\' + 'htmlFlies')) {
        //     fs.mkdirSync(dirName + '\\' + 'htmlFlies')
        // }
        files.forEach((file, index) => {
            // 筛选markdown文件
            if (file.includes('.md')) {
                let content = fs.readFileSync(dirName + '\\' + file, 'utf-8')
                //去掉头部标签
                content = content.replace(/^\-{3}.*?\-{3}/s, '')
                let html = content
                //                 let html = marked.parse(content)
                //                 //通过cdn添加样式
                //                 html = `<link href="https://cdn.bootcss.com/highlight.js/9.18.1/styles/monokai-sublime.min.css" rel="stylesheet">
                // <script src="https://cdn.bootcss.com/highlight.js/9.18.1/highlight.min.js"></script>
                // <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.1.0/github-markdown.min.css"/>
                // <article class="markdown-body">`+ html + `<script>hljs.initHighlightingOnLoad();</script><style>.markdown-body pre{background-color: #21272c;}</style>
                // </article>`
                //写入同名文件中
                // fs.writeFileSync(`${dirName + '\\' + 'htmlFlies'}/${file.split('.')[0]}.html`, html)
                //定义sql语句，查询文件名是否被占用
                const sqlStr = 'select * from blog where name=?'
                db.query(sqlStr, file, (err, results) => {
                    //执行sql语句失败
                    if (err) {
                        reject(err)
                    }
                    //判断这篇博客是否被已经存在
                    if (results.length > 0) {
                        // 选择覆盖之前的数据
                        const sqlStr2 = 'update blog set ? where ?'
                        db.query(sqlStr2, [{ htmlData: html }, { name: file }], (err, res) => {
                            if (err) {
                                reject(err)
                            }
                        })
                    } else {
                        // 没有问题, 插入入数据
                        const sqlStr2 = 'insert into blog set ?'
                        db.query(sqlStr2, { name: file, htmlData: html }, (err, results) => {
                            if (err) {
                                reject(err)
                            }
                        })
                    }

                })
            }
        })
        resolve("成功更新博客!")
    })
}
