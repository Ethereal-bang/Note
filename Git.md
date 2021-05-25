# Git 安装

1. 安装完成后在开始菜单找到 Git Bash，说明安装成功

2. 如图设置机器的名字和 Email 地址

    ![image-20210518193114993](https://i.loli.net/2021/05/18/e2SunaHrcbpdmGJ.png)



# 创建版本库

+ <span style="font-size:20px">版本库的作用</span>

    <hr>

    版本库（*repository*），简单理解成目录，在目录里面的所有文件都可以被 Git 管理起来，追踪每个文件的修改、删除，以便任何时刻都可以还原

+ <span style="font-size:20px">创建版本库</span>

    <hr>

    1. 在一个合适的地方手动创建一个空目录，例如`learngit`

    2. 通过`git init`命令把这个目录变成 git 可管理的仓库，得到当前目录下多了一个`.git`目录，是 git 用来跟踪管理版本库



+ <span style="font-size:20px">把文件添加到版本库</span>

    <hr>

    以`readme.txt`文件为例。

    1. 创建一个`readme.txt`文件并放入`learngit`或其子目录下

    2. 将 git 目录切换至`learngit`或直接在文件夹中找到该文件右键`Git Bash Here`

    3. `git add`命令告诉 git **将文件添加到仓库**，这里是`git add readme.txt`

    4. `git commit`命令告诉 Git，将**文件提交到仓库**

        ```
        $ git commit -m "wrote a readme file"
        [master 522b804] wrote a readme file
         1 file changed, 1 insertion(+), 2 deletions(-)
        ```

        `-m`后输入的是本次提交的说明，这样能从历史记录里方便的找到改动记录

        命令执行成功后，提示`1 file changed`：1个文件被改动（新添加的readme.txt文件）；`1 insertions`：插入了一行内容（readme.txt有一行内容）。

    

    为什么 Git 添加文件需要 add、commit 总共两步。因为`commit`可以一次提交多个文件，所以可以向下例这样：

    ```
    $ git add file1.txt
    $ git add file2.txt file3.txt
    $ git commit -m "add 3 files."
    ```



# 恢复

+ <span style="font-size:20px">使用场景</span>

    <hr>

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

+ <span style="font-size:20px">版本回退</span>

    <hr>

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

    

# 分支管理

==。。。==



# 参考链接

[Git教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/896043488029600)