const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const ReactRefreshWebpackPlugin = require("@pmmmwh/react-refresh-webpack-plugin");

const mode = process.env.NODE_ENV !== 'production';

module.exports = {
    mode: mode ? "development" : "production", // 배포시 production으로
    name: "ToDoTogether",

    resolve: {
        extensions: [".js",".jsx"]
    },
    entry: {
        main: "./index"
    },
    module: {
        rules: [
            {
                test:/\.(js||jsx)$/,
                loader: "babel-loader",
                options: {
                    presets: [["@babel/preset-env",{targets: {browsers: ["last 2 versions",">= 5% in KR"]}}],"@babel/preset-react"],
                    plugins: ["react-refresh/babel",
                             ["transform-remove-console", {exclude: ["error","warn","log"]}]
                             ] 
                },
            },
            {
                test:/\.(css||sass||scss)$/,
                use: ["style-loader","css-loader","sass-loader"]
            },
            {
                test:/\.(png||jpg||gif||svg)$/,
                loader: "file-loader"
            },
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./public/index.html"
        }),
        new ReactRefreshWebpackPlugin(),
        new CleanWebpackPlugin()
    ],
    output: {
        path: path.join(__dirname, "./dist"),
        filename: "[name].js",
        publicPath: "/"
    },
    devServer: {
        port: 3000,
        historyApiFallback: true, // 라우터 사용시 필요한 기능이다. 변경된 주소에서 새로고침해도 에러를 막아준다.
        proxy: {
            "/api": {
                target: "http://localhost:8080",
                pathRewrite: {"/api":"/"}
            }
        },
        devMiddleware: {publicPath: "/"},
        static: path.resolve(__dirname, "./dist"),
        hot: mode ? true : false
    }
}