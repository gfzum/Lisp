# Lisp

## Todo

- 基本算数、逻辑表达式
- 定义变量：`(define a 3)` 
- 定义函数：`(define (func x) (* x x))`，可作为参数传递，也可作为返回值，还可子函数局部化 p19
- 条件表达：
```lisp
cond( 
    ((> x 0) x)
    ((= x 0) 0)
    ((< x 0) (- x))
)

(if (< x 0) (- x) x)
```
- `lambda` p59
- `let` ?

## Question

p10 提到的正则序求值和惰性求值是一回事吗？