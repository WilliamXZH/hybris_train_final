############################################

1.  所有开发者在进行开发的时候，创建一个自己的分支，以自己的名字命名
2.  项目进行clone以后可以直接将目录中的文件拷贝到{HYBRIS_HOME}目录-------{HYBRIS_HOME}目录为bin,config,data,temp目录的的上一级目录
3.  以后的操作都是对于{HYBRIS_HOME}目录进行各种操作
4.  所有操作都不允许对master分支进行操作，dev分支为开发分支，dev分支进行测试成功后，再转到master分支进行上线
5.  遇到问题及时记录并进行讨论
6.  拷贝完成后，自行对local.properties进行修改，由于用到了mysql数据库，根据信息自行配置
7.  拷贝完成后，记得在platform/lib/dbdiver中加入数据库驱动包
8.  如果本地数据库为5.7以上记得加上         mysql.workaround.index.langpk=true
9.  本地hybris环境用新的环境，防止以前的代码提交

###########################################