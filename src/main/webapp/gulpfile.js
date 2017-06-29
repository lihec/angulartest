var gulp = require('gulp');
var livereload = require('gulp-livereload');
var clean = require('gulp-clean');

gulp.task('clean-dist', function () {
    return gulp.src('dist/*.*', {read: false})
        .pipe(clean());
});

gulp.task('watch', function () {    // 这里的watch，是自定义的，写成live或者别的也行
    livereload.listen();
    // app/**/*.*的意思是 app文件夹下的 任何文件夹 的 任何文件
    gulp.watch('src/**/*.*', function (file) {
        gulp.start('clean-dist');
        setTimeout(function () {
            livereload.reload();
        }, 3000);
    });
});