const express = require("express");
const app = express();
const server = require("http").createServer(app);
const history = require("connect-history-api-fallback");

app.use(history());
app.use(express.static("./dist"));

// app.listen(80);
server.listen(7003, function listening() {
    console.log(
        // '服务器启动成功：' + global.config.host + ':' + global.config.port
        "static-server 启动成功：" + "localhost" + ":" + "7003"
    );
});
