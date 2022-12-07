# 格式

## 排版

**document:**

```latex
\begin{document}
\end{document}
```

**分区：**

```latex
\section{自我评价}
\section{教育经历}
\section{个人兴趣}     
```

**间距：**

`\hspace` 用于水平间距 (horizon)，`\vspace` 用于垂直间距 (vertical)

`/hfill`

**缩进：**

`\noindent`

## 文本

**加粗：**

```latex
\textbf{Involutionist}
```

**超链接：**

```latex
\href{https://github.com/Ethereal-bang}{Etheral-bang}
```

**换行：**

`{text}\\`

**空格:**

`\enspace`, `a\ b `



## 列表

```latex
\begin{itemize}
  \item item1
  \item item2
\end{itemize}
```



## fontawesome Icons

加载 fontwesome package 后，使用 '\\faicon' 的形式显示对应的 icon

```latex
% use fontawesome
\RequirePackage{fontawesome}

\faGithub\enspace\href{https://github.com/Ethereal-bang}{github.com/Ethereal-bang}
```



# Project

**配置文件：**setting.cls

```latex
\ProvidesClass{setting}[2010/07/10 v0.9 Resume class]
```

**tex 文件：**

```latex
\documentclass{setting} % Use the custom style
```



# Package

**\RequirePackage{}:**

`\RequirePackage{fontawesome}`

**\usePackage{}:**

```latex
\usepackage[left=0.4 in,top=0.4in,right=0.4 in,bottom=0.4in]{geometry} % Document margins

% link style
\usepackage[colorlinks = true,
            linkcolor = cyan,
            urlcolor  = cyan,
            citecolor = cyan,
            anchorcolor = cyan]{hyperref}
```



# New Command

```latex
% 定义
\newcommand{\contactInfo}[4]{
    \centerline{
        \normalsize{
            \faHome\  {#1} \quad
            \faPhone\ {#2} \quad 
            \faEnvelopeO\  \href{mailto:{#3}}{#3} \quad 
            \faGithub \href{https://#4}{#4}
        }
    }
    \vspace{0.7ex}
}
% 使用
\contactInfo{Qijiang District,Chongqing City}{+86 15086861179}{shen.ruofeng@qq.com}{github.com/Etheral-bang}
```



# Resume

## Projects

**Bullet Point:**



## STAR 法则

Situation 情境: 工作背景 

Task: 负责做什么

Action 行动：做了什么

Result：工作取得了什么样的结果



# DEBUG

[Overleaf 中文乱码](https://blog.csdn.net/super_dmz/article/details/106015731)

+ Solution:
    1. menu里面设置 Compiler 为XeLaTeX; 
    2. tex文件开头加入语句：`\usepackage[UTF8]{ctex}`