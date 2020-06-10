# algorithm

#### 介绍
算法学习(algorithm studying)

#### 软件架构
仅为java项目，j2ee,个人学习使用，如有问题，请指出


#### 安装教程

1.  使用git直接拉取dev分支即可：  git clone https://gitee.com/dr_dark/algorithm.git   --origin dev

#### 项目说明

1.  dichotomy项目：二分法查找
````
二分法查找背景：当数组或者集合数据量很大的时候，想要定位某一个元素，或者查询某一个元素是否存在的时候，
常用的方法是使用使用循环的方式，遍历每一个元素，直到查询到所需元素位置，这样的方式查询效率非常低
这个时候，就需要二分法查找'
二分法查找时间复杂度：O(log n)
算法问题：
//第一种：
int mid = (low + high) / 2;
//第二种
int mid = low + (high - low)/2;
看着没啥区别，但是第一种存在在极端情况下，(low + high)存在着溢出的风险，进而得到错误的mid结果，导致程序错误。
但是第二种能够保证计算出来的mid，一定大于low，小于high，不存在溢出的问题。
缺点：首先，数组一定要排序，无序的话不能使用二分法，使用二叉树进行操作，其次只能使用在数组上，不能使用在链表中，
数组在查找的时候时间复杂度是O(1)，但是在删除和修改的时间复杂度是O(n)，所以在排序的时候，就要花费大量的时间。降低效率
````
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
