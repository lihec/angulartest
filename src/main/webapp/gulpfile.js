var gulp = require('gulp');
var livereload = require('gulp-livereload');
var clean = require('gulp-clean');
var gutil = require("gulp-util");
var _ = require('lodash');
var webpack = require('webpack');
var webpackConfig = require('./config/webpack.prod.js');

//清空打包目录
gulp.task('clean:dist', function () {
    return gulp.src('dist/*.*', {read: false})
        .pipe(clean());
});

//监控前端文件修改，运行webpack打包
gulp.task('build:watch',['webpack:build-dev'], function () {    // 这里的watch，是自定义的，写成live或者别的也行
    livereload.listen();
    // app/**/*.*的意思是 app文件夹下的 任何文件夹 的 任何文件
    gulp.watch('src/**/*.*', function (file) {
        gulp.start('webpack:build-dev', function () {
            // 在webpack:build-dev运行完成后执行下面的代码，前提条件是在定义webpack:build-dev这个的时候，接收了一个毁掉函数function(callback)
            livereload.reload();
        });
    });
});

//webpack打包，在打包之前先清空打包目录
gulp.task("webpack:build-dev", ['clean:dist'], function(callback) {
    // run webpack
    webpack(webpackConfig).run(function(err, stats) {
        if(err) throw new gutil.PluginError("webpack:build-dev", err);
        gutil.log("[webpack:build-dev]", stats.toString({
            colors: true
        }));
        callback();
    });
});


gulp.task("webpack:watch", ['clean:dist'], function() {
    livereload.listen();
    // run webpack
    var watchConfig = _.merge(webpackConfig, { watch: true });
    webpack(webpackConfig).watch(200, function(err, stats) {
        if(err) throw new gutil.PluginError("webpack:build-dev", err);
        gutil.log("[webpack:build-dev]", stats.toString({
            colors: true
        }));
        livereload.reload();
    });
});

// gulp.task("build-dev", ["webpack:build-dev"], function() {
//     gulp.watch(["src/**/*"], ["webpack:build-dev"]);
// });

gulp.task('default', function() {
    gulp.start('build:watch');
});




//
gulp.task('webpack', ['clean:dist'], function(callback) {
    webpack(webpackConfig, function(err, stats) {
        console.log("err="+err+",stats="+stats);
        callback();
        console.log("webpack succeed");
    });
});

// 监听模式
gulp.task('watch:webpack', ['clean:dist'], function() {
    var watchConfig = _.merge(webpackConfig, { watch: true });
    webpack(watchConfig).watch(200, function(err, stats) {
        console.log("err="+err+",stats="+stats);
        console.log("webpack succeed");

    });
});