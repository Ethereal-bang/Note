+ <span style="font-size:20px">常用</span>

    **`git status`**、**`git add a.txt`**、**`git commit -m " "`**、**`git push origin main`**



**.gitignore** 



# Git 安装

**Mac: (by homebrew)**

```shell
brew install git
```



1. 安装完成后在开始菜单找到 Git Bash，说明安装成功

2. 如图设置机器的名字和 Email 地址

    ![image-20210518193114993](https://i.loli.net/2021/05/18/e2SunaHrcbpdmGJ.png)



# 创建版本库

<span style="font-size:20px">创建版本库</span>

`git init` 命令把当前目录变成 git 可管理的仓库，得到当前目录下多了一个 `.git` 目录，是 git 用来跟踪管理版本库



# git add

为什么 Git 添加文件需要 add、commit 总共两步。因为`commit`可以一次提交多个文件，所以可以向下例这样：

```
$ git add file1.txt
$ git add file2.txt file3.txt
$ git commit -m "add 3 files."
```



# Commit

**git status:**

+ Changes to be committed——待提交
+ Changes not staged for commit——未加入暂存区的改动
+ Untracked files——未追踪文件

## 暂存区

**git add: **

```shell
$git add src/1.js src/2.js	# git add [file] [file]
$git add .	# 添加所有文件
```

**撤销添加:**

```shell
git reset --mixed	# 所有退出暂存区 但修改保留
```

## Commit

（Commitizen）

```shell
$git cz
```



# Push

```shell
$ git push -u origin <localBranch>:<remoteBranch> # -u即--set-upstream
```



# 恢复

+ <span style="font-size:20px">使用场景</span>

    
对上例的`readme.txt`文件再次修改后提交：
    
```
    $git add raedme.txt
    $git mommit -m "append GPL"
    ```
    
像这样不断对文件进行修改，然后不断提交修改到版本库里。每当你觉得文件修改到一定程度时，就可以“保存一个快照”，这个快照在 Git 中被称为`commit`。一旦把文件改乱了或误删，还可以从最近的一个`commit`恢复



+ <span style="font-size:20px">查看版本</span>

    <hr>

    在 Git 中，可以用**`git log`**命令查看历史记录，例如：

    ```
    $ git log
    commit 522b804a61fc8fee0daac23b51ffffa0bc9e466f (HEAD -> master)
    Author: Ethereal-bang <939847757@qq.com>
    Date:   Tue May 18 20:06:33 2021 +0800
    
        wrote a readme file
    
    commit 187004ff973252410a4e8ff4b85a7c9538f5bd7a
    Author: HP <939847757@qq.com>
    Date:   Mon Oct 5 22:55:57 2020 +0800
    
        wrote a readme file
    ```

    `git log`命令显示从最近到最远的提交日志，如果嫌输出信息太多可以加上**--`pretty=oneline`**参数：

    ```
    522b804a61fc8fee0daac23b51ffffa0bc9e466f (HEAD -> master) wrote a readme file
    187004ff973252410a4e8ff4b85a7c9538f5bd7a wrote a readme file
    ```

    前面的一堆编码是`commit id`（*版本号*）

+ <span style="font-size:20px">版本回退 / 撤销变更</span>

    **版本表示**：`HEAD`表示当前版本，而上一个版本就是`HEAD^`，顺推下去上上个版本就是`HEAD^^`。往上 100 个版本还可以写成`HEAD~100`。
    
    
    

要将版本回退到上一个版本（*下例中的 `add distributed`版本*），就可以使用**`git reset`**命令：
    
``` 
    $ git reset --hard HEAD^
    HEAD is now at e475afc add distributed
```

此时再**`git log`**时，最新的版本已经看不到了。
    
想要再返回刚才的版本，可以找到该版本的`commit id`后回退：
    
```
    $ git reset --hard 1094a
    HEAD is now at 83b0afe append GPL
```

版本号没必要写全，Git 会自动去找
    
找不到版本号记录时，还能使用**`git reflog`**查看命令历史，再找到 commit id
    


+ <span style="font-size:20px">管理修改</span>

    <hr>
    
    + 场景一：乱改了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令`git checkout -- file`。
    + 场景二：不但乱改了工作区某个文件的内容，还添加了暂存区时，想丢弃修改。首先用命令`git reset HEAD <file>`回到场景一，再按场景一操作。
    + 场景三：已经提交了不合适的修改到版本库，想要撤销本次提交，使用版本回退，前提时没有推送到远程库。



# 删除文件

命令`git rm`用于删除一个文件，如果一个文件已经被提交到版本库，那么不用担心误删。命令`git checkout`可以将修改或删除一键还原，本质是用版本库里的版本替换工作区的版本



# 远程仓库

## Clone

```shell
$git clone https://github.com/<>/<>.git
```

就会在当前路径下新建对应文件夹

**指定分支：** `-b develop`



## 添加远程库

+ <span style="font-size:20px">使用场景</span>

    <hr>

    已经在本地创建了一个 Git 仓库后，又在 GitHub 上创建一个 Git 仓库，并且让这两个仓库进行远程同步。这样，GitHub 上的仓库既可以作为备份，又可以让其他人通过该仓库来协作

+ <span style="font-size:20px">步骤</span>

  <hr>
  
  1. 关联一个远程库，使用命令` git remote add origin git@github.com:Ethereal-bang/qinfenfeng.git`，关联时`origin`是默认习惯命名
  2. 关联后，使用命令`git push -u origin main`第一次推送 master 分支的所有内容，Github 上即可以看到远程库的内容已和本地一模一样。
  3. 此后，每次提交后如有必要都可以使用命令**`git push origin main`**把本地`main`分支的最新修改推送至 GitHub。
  
+ <span style="font-size:20px">解绑远程库</span>

    <hr>

    先用`git remote -v`查看远程库信息，后根据名字用命令`git remote rm`删除远程库，如：

    ```
    git remote rm origin
    ```

+ <span style="font-size:20px">关于 SSH 公钥</span>

    <hr>

    [Git - 生成 SSH 公钥](https://git-scm.com/book/zh/v2/%E6%9C%8D%E5%8A%A1%E5%99%A8%E4%B8%8A%E7%9A%84-Git-%E7%94%9F%E6%88%90-SSH-%E5%85%AC%E9%92%A5)

    ```
    The key fingerprint is:
    SHA256:msnTJK36rmLUqSi9Lgg+jcZQDaUrHBIh8gJJmmJhgAY HP@LAPTOP-UMVG4BNH
    The key's randomart image is:
    +---[RSA 3072]----+
    |E* ..            |
    |O=o.             |
    |Booo             |
    |=.o..  .         |
    |.o.. .. S        |
    |o.. o. O         |
    |*+o.  B .        |
    |=*=. . .         |
    |o++oo+o          |
    +----[SHA256]-----+
    ```

    公钥：（*第一行与第二行只隔了一个空格*）

    ```
    ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDgswoAX8lc29tZaXoSDnsJK1SyH1HmVYFbqWdun+/dMNA5EIreP/vBOajpQl3ypbo1iq6MUGUO8th18eNY063P/vsCoXAf29WGCJFCYUN+5MrjFALFTKaebSW/70ouHG5ul90rX+LKSd60fXQ2cKrWEXfaXiR+ntggUO+rqNiig4d0nqwg8P+Xl73iTEpFNNGNFMrpb/gdl4y/YBVZwsMWxLtRpJ5vSt8hUxeDK1tcnuUF7WK0ydxY4oDZENnlkq7kd9BVDXbBA/ECL1XqxMY99azEmhCe0lMmqKbluJIflF7htdpvU/KTPfET/bjbsjOi0ah6ndZ2jLRhfpSxQ4auDKF0yypBl8QICnVi8+IfyI9Oo2yggcKgqNDCFYLin+i5apb8G2VZcKCSibCxyYyyPdwgyeuUkQuZmwTqd/+dmRqObQAtBZnmEK75PiJBBVwX5IWvLvCDmkueOZyFbnOwKirpujEIzt9NRGlLSgdwi/sUnPHBAWI+NLEhboBfPNc= HP@LAPTOP-UMVG4BNH
    ```




## Fetch

1. 配置原仓库路径：

    ```
    git remote add upstream git@github.com:ceo-analog-system/ceo.git
    ```

    `git remote -v`后可以看到远程仓库多了个原仓库地址。

2. 抓取原仓库修改文件：

    ```
    $ git fetch upstream
    From github.com:ceo-analog-system/ceo
     * [new branch]      dev        -> upstream/dev
     * [new branch]      main       -> upstream/main
    ```

3. 合并远程仓库的 master 分支。

    ```
    $ git merge upstream/main
    Already up to date.
    ```


这时本地仓库已经与原仓库完全同步，冲突需解决后合并。



```shell
$ git pull origin main --alow-unrelated-histories
```

> 解决: fatal: refusing to merge unrelated histories



## Pull

git fetch + git merge

 

# 分支

## 提交及使用分支

创建分支：`git branch <分支名>`

切换分支：`git checkout <分支名>`

创建新分支并切换：`git checkout -b <分支名>`



## 合并分支

两种方法：

1. `git merge <分支名>`

2. `git rebase <目标分支>`

    取出一系列的提交记录并复制，创造更线性的提交历史



## 删除

```shell
$ git branch -d dev-s	# -d表示仅当分支已完全合并到其上游分支中时才会删除该分支
```



# 在提交树上移动

## 分离的 HEAD：让 HEAD 指向提交记录

HEAD 通常指向分支名，大多数修改提交树的 git 命令都是从改变 HEAD 的指向开始的。

1. `git checkout <提交记录的哈希值>`

    > 实际应用中不得不使用`git log`查看提交记录的哈希值。哈希值在 GIT 中很长，因此只需要提供能唯一标识提交记录的前几个字符即可。

### 相对引用：

两种基本用法：

1. 使用`^`向上移动 1 个提交记录
2. 使用`~<num>`向上移动多个提交记录

将操作符`^`加在引用名称的后面，标识让 GIT 寻找该指定提交记录的父提交，如`git checkout main^`：

![image-20210729111204446](https://i.loli.net/2021/07/29/XME5RelrLqwaSBJ.png)

可以配合 HEAD 向上一直移动。

#### 强制修改分支位置

使用相对移动最多的就是移动分支，可以直接使用`-f`选项让分支指向另一个提交



# SSH

1. 生成 ssh key

    ```shell
    ssh-keygen -t rsa
    ```

2. GitHub New SSH key：

    复制 id_rsa.pub 内容



# Husky

> [如何用 Husky 向 Git 添加 Commit Hooks 以实现代码任务的自动化](https://www.freecodecamp.org/chinese/news/how-to-add-commit-hooks-to-git-with-husky-to-automate-code-tasks)

Git Hook 工具，可以监听 git 的每一步操作

```shell
$ npm i husky -D
$ npx husky install # 添加钩子
$ # 创建钩子
```



package.json 配置:

```json
"scripts": {
  "prepare": "husky install"
}
```



# Git Commit 规范提交

> [规范化Commit Commitizen + Husky + commitlint](https://note.zhangjc.cn/src/6_%E5%B7%A5%E7%A8%8B%E5%8C%96/Git/commitizen&husky&commitlint.html)

**Commitizen:** 基于 Node.js 的 `git commit` 命令行工具，辅助生成标准化规范化的 commit message

**Commitizen Adapter:** 更换 commitizen 命令行工具的 交互方式 插件

**[git-cz](https://github.com/streamich/git-cz):** 语义化 git commits 工具

**[commitlint](https://commitlint.js.org):** git message 校验工具



1. **commitizen 全局/项目 安装:**

    ```shell
    $ npm i -g commitizen
    ```

2. **初始化 Adapter:** [cz-conventional-changelog](https://github.com/commitizen/cz-conventional-changelog)

    ```shell
    $ npx commitizen init cz-conventional-changelog --save-dev --save-exact
    $ pnpm add -D -E cz-conventional-changelog
    ```

    ```json
    "config": {
      "commitizen": {
        "path": "cz-conventional-changelog"
      }
    }
    ```

3. **git-cz 代替 git commit**



1. **配置 commitlint** (要先安装 husky) 

    ```
    npm install -g @commitlint/cli @commitlint/config-conventional
    ```

    

# WebStorm + Git

**推送到 GitHub：**

在本地分支上 push 时，要选择 main 远程仓库才对应的是 GitHub 上地址。



# GitHub

## SSH 连接

1. 生成新 SSH 密钥:

   ```bash
   $ ssh-keygen -t ed25519 -C "your_email@example.com"
   Your identification has been saved in /Users/srf/.ssh/id_ed25519
   ```

2. 添加到 ssh-agent:

   1. 后台启动 ssh 代理:

      ```bash
      $ eval "$(ssh-agent -s)"
      ```

   2. 没有则创建，修改 `~/.ssh/config` 文件包含以下内容

      ```bash
      $ vi
      Host github.com
        AddKeysToAgent yes
        IdentityFile ~/.ssh/id_ed25519
      ```

   3. 将 SSH 私钥添加到 ssh-agent 并将密码存储在密钥链中

      ```bash
      $ ssh-add ~/.ssh/id_ed25519
      Identity added: /Users/srf/.ssh/id_ed25519 (939847757@qq.com)
      ```

3. 将 SSH 公钥添加到 GitHub 上的帐户
   1. 将 SSH 公钥复制: `pbcopy < ~/.ssh/id_ed25519.pub`
   2. GitHub 中添加 SSH keys。(Title 填计算机昵称，key 粘贴)



## Github pages 部署

> 要使用 GitHub Pages 功能实现全面效果，项目应该被构造为典型的网站——index.html 入口，原生 JS 等



# ERROR

`fatal: unable to access 'XXX': Recv failure: Connection was reset`:

[修改设置解除ssl验证：`git config --global http.sslVerify "false"`，然后重新clone](https://blog.csdn.net/hero_java/article/details/115036756)



**Failed to connect to github.com port 443: Time out** 

Solution 1: http 代理

```bash
git config --global --unset http.proxy
```

Solution 2: —— [更改 IP](https://juejin.cn/post/6844904193170341896) 解决 DNS 污染

先尝试 `ipconfig /flushdns`

查询对应 IP Address，本地设置直链 IP，不走运营商的 DNS:

[github.com IP](https://www.ipaddress.com/site/github.com) —— 140.82.112.4 / 140.82.113.3

[github.global.ssl.fastly.net IP](https://fastly.net.ipaddress.com/github.global.ssl.fastly.net#ipinfo) —— IPv4:

151.101.1.6

151.101.65.6

151.101.129.6

151.101.193.6

[assets-cdn.github.com](https://github.com.ipaddress.com/assets-cdn.github.com) —— 140.82.112.4

```shell
# hosts文件
# github
140.82.113.3 github.com
151.101.1.6 github.global.ssl.fastly.net
151.101.65.6 github.global.ssl.fastly.net
151.101.129.6 github.global.ssl.fastly.net
151.101.193.6 github.global.ssl.fastly.net
140.82.112.4 assets-cdn.github.com
```

```shell
ipconfig /flushdns
```



**10054:** —— 关闭 SSL 验证

```bash
git config set --global http.SslVerify "false"
```



**[fatal: unable to access 'https://github.com...\': Empty reply from server](https://stackoverflow.com/questions/27087483/how-to-resolve-git-pull-fatal-unable-to-access-https-github-com-empty):**—— http 代理

```bash
git config --global --unset http.proxy
```



**Failed to connect to raw.githubusercontent.com:**——修改hosts 文件

```shell
sudo nano /etc/hosts
```

```
# github
185.199.108.133 raw.githubusercontent.com
```



无法访问 [HTTP/2 stream 1 was not closed cleanly before end of the underlying stream](https://blog.csdn.net/zz00008888/article/details/123529805): ——更改默认协议为 HTTP/1.1

```shell
$ git config --global http.version HTTP/1.1
```

