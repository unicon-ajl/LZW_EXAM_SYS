<p></p>
<p></p>

# 开源考试系统 - Mysql版

## 项目介绍

开源考试系统是一款 java + vue 的前后端分离的考试系统。主要优点是开发、部署简单快捷、界面设计友好、代码结构清晰。支持web端和微信小程序，能覆盖到pc机和手机等设备。
支持多种部署方式：集成部署、前后端分离部署、docker部署。

* QQ：`2290501132`

### 仓库版本地址


* gitee ：[https://gitee.com/fallen-leaves-mourn/exam](https://gitee.com/fallen-leaves-mourn/exam)


* mysql : ![Alt text](/imgs/img.png)

### 技术找

|  环境   | 版本  |
|  ----  | ----  |
| 操作系统  | Windows / Linux  |  
| NodeJs  | 14  |  
| Jdk  | 1.8  |  
| Mysql  | 8.0  |

### 开发说明

* 数据库，创建表初始化数据，数据库名称为xzs
* 代码下载 mysql 版本，配合相应的数据库使用
* 安装mysql ，导入xzs-mysql.sql脚本
* 学生端默认账号：student / 123456
* 管理端默认账号：admin / 123456


### 后端开发

* /xzs-mysql/source/xzs为后台代码，建议使用IntelliJ IDEA打开
* 打开application-dev.yml文件中，配置好mysql的服务地址
* 去七牛云官网申请好对象存储账号，修改application.yml中的qn相关的配置，七牛云主要用于文件存储(为了节约成本，我们这里使用本地存储，可忽略)
* 启动后台程序,默认端口为8000
* 学生系统地址：http://ip:8000/student
* 管理端地址：http://ip:8000/admin


### 前端开发

* 前端使用 vscode，分别打开文件夹打开源代码source\vue\xzs-student和source\vue\xzs-admin
* 在终端执行下面命令，安装node_module：
<!-- 安装依赖，即 node_module 文件夹 -->
> npm i

* 在终端执行下面命令，启动前端代码
<!-- 运行前端代码 -->
> npm run serve


### 小程序开发

* 去腾讯小程序官网(https://mp.weixin.qq.com/cgi-bin/wx)注册账号，拿到appid和secret信息
* 下载好微信小程序开发工具
* 打开工具，导入代码 \source\wx\xzs-student
* 修改application.yml文件里的wx配置下面的appid和secret
* 启动微信小程序开发工具

### 代码结构简介

* 后端系统
- source 代码文件夹
 - xzs 后端代码
  - src 
   - main
    - java
      - xzs
       - base   项目基础类
       - configuration   springboot的基础配置
       - context   项目上下文
       - controller
        - admin   管理端控制器
        - student   学生端控制器
        - wechat   小程序控制器
       - domain   领域对象
       - event   事件驱动模型，配合监听器一起使用
       - exception   业务异常
       - listener   监听器，配合event使用
       - repository   数据仓储，mybaties的映射Mapper
       - service   服务
        - impl   服务实现
       - utility   工具类
       - viewmodel   视图、接口类
        - admin   管理端视图类
        - student   学生端视图类
        - wx   微信端视图类
      - resources
       - mapper   数据仓库的sql文件


* 学生端

- source 代码文件夹
 - vue 后端代码
  - xzs-student  学生端代码
   - public 公共文件、主页
   - src 前端代码
    - api 接口地址
    - assets 图片资源
    - components 公共组件
     - BackToTop 返回顶部组件
     - Pagination 分页组件
     - SvgIcon 图标组件
     - Ueditor 编辑器组件
    - icons 图标库
    - layout 母版页
    - store 状态管理
    - styles 主题样式
    - utils 公共方法
    - views 视图、系统页面


* 微信端

- source 代码文件夹
 - wx 微信端代码
  - xzs-student  学生端代码
   - assets 图片资源文件
   - component 组件库
    - iView 主题
   - pages 小程序页面
   - utils 公共方法
   - wxs 页面公共方法


* 管理端

- source 代码文件夹
 - vue 后端代码
  - xzs-student  学生端代码
   - public 公共文件、主页
   - src 前端代码
    - api 接口地址
    - assets 图片资源
    - components 公共组件
     - BackToTop 返回顶部组件
     - Pagination 分页组件
     - SvgIcon 图标组件
     - Ueditor 编辑器组件
    - icons 图标库
    - layout 母版页
    - store 状态管理
    - styles 主题样式
    - utils 公共方法
    - views 视图、系统页面


### 学生系统功能

|  模块   | 介绍  |
|  ----  | ----  |
| 登录  | 用户名、密码  |  
| 注册  | 年级、用户名、密码  |  
| 任务中心  | 管理员发布的年级任务，每个学生只能做一次  |  
| 考试  | 题干支持文本、图片、数学公式、表格等，学生答题支持：文本  |  
| 固定试卷  | 可重复练习、自行批改的试卷  |  
| 时段试卷  | 在时间限制内，可重复练习、自行批改的试卷  |  
| 考试记录  | 查看答卷记录和试卷信息  |  
| 错题本  | 答错题目会自动进入错题本，显示题目基本信息  |  
| 个人信息  | 显示学生个人资料  |  
| 更新信息  | 修改个人资料、头像  |  
| 个人动态  | 显示用户最近的个人动态  |  
| 消息中心  | 用于接收管理员发送的消息  |  

### 管理系统功能

|  模块   | 介绍  |
|  ----  | ----  |
| 登录  | 用户名、密码  |  
| 主页  | 试卷总数、题目总数、用户活跃度、题目月数量  |  
| 学生列表  | 显示系统所有的学生，新增、修改、删除、禁用  |  
| 管理员列表  | 显示系统所有的管理员，新增、修改、删除、禁用  |  
| 学科列表  | 学科查询、修改、删除  |  
| 学科创编  | 创建学科  |  
| 试卷列表  | 试卷查询、修改、删除  |  
| 试卷创编  | 创建的试卷为时段试卷、固定试卷、任务试卷  |  
| 题目列表  | 题目查询、修改、删除  |  
| 题目创建  | 题目支持单选题、多选题、判断题、填空题、简答题，题干支持文本、图片、表格、数学公式  |  
| 任务列表  | 任务查询、修改、删除  |  
| 消息列表  | 显示已发送的消息，消息已读人数等信息  |  
| 消息发送  | 发送消息给多个用户  |  
| 用户日志  | 显示所有用户日志  |  
| 个人资料  | 显示管理员用户名、真实姓名  |  
| 时间线  | 显示管理员创建时间  |  
| 修改资料  | 修改姓名、手机号  |  

### 小程序功能

|  模块   | 介绍  |
|  ----  | ----  |
| 登录  | 用户登录登出功能，登录会自动绑定微信账号，登出会解绑  |  
| 注册  | 年级、用户名、密码  |  
| 任务中心  | 管理员发布的年级任务，每个学生只能做一次  |  
| 考试  | 题干支持文本、图片、数学公式、表格等，学生答题支持：文本  |  
| 固定试卷  | 可重复练习、自行批改的试卷  |  
| 时段试卷  | 在时间限制内，可重复练习、自行批改的试卷  |  
| 考试记录  | 查看答卷记录和试卷信息  |  
| 错题本  | 答错题目会自动进入错题本，显示题目基本信息  |  
| 个人信息  | 显示学生个人资料  |  
| 更新信息  | 修改个人资料、头像  |  
| 个人动态  | 显示用户最近的个人动态  |  
| 消息中心  | 用于接收管理员发送的消息  |  



### 系统展示



* 学生考试系统
![Alt text](/imgs/img-1.png)
![Alt text](/imgs/img-2.png)

* 小程序考试系统
![Alt text](/imgs/img-3.png)
![Alt text](/imgs/img-4.png)
![Alt text](/imgs/img-5.png)
![Alt text](/imgs/img-6.png)
![Alt text](/imgs/img-7.png)
![Alt text](/imgs/img-8.png)
![Alt text](/imgs/img-9.png)
![Alt text](/imgs/img-10.png)

* 后台管理系统
![Alt text](/imgs/img-11.png)
![Alt text](/imgs/img-12.png)
