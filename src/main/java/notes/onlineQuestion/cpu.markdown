## 压测不久CPU飙升报警
1. 使用top命令查看是哪个进程，确认是自己项目的进程导致cpu飙升后
2. 使用arthas-thread命令，（thread）、（thread -n 3）命令查看最忙碌的线程 </br>
初步结论：全是垃圾回收器的线程，是jvm虚拟机配置不对，使用了jdk8默认的垃圾回收器，parallel 垃圾回收器，占用太多线程进行垃圾回收
3. 所以修改垃圾回收器，使用ParNew + cms
4. 上线后cpu依旧飙升
5. 再使用arthas-thread 查看，发现都卡在了一个密钥加密处，经过排查，加密的密文每次都重新加载到内存，
特别占用空间
6. cpu降下来以后，内存迟迟降不下来，arthas-memory、jmap -heap pid 都可以用来查看堆的使用情况
   jmap -heap pid查看内存使用多少，jstat -gcutil pid查看内存使用率以及gc次数
   这两个命令可以使用arthas-memory查看内存使用多少及使用率，dashboard 查看gc次数
   jvm查看虚拟机配置参数，可代替jinfo -pid


客户端上线新的功能展示入口，复用之前的服务端接口，导致服务端接口流量增加至之前的两倍；
### 第一轮优化
查看线程执行情况，发现大部分线程都卡在一个加密处，最后发现进程都blocked在读取密钥文件这一步，每次加密都需要把私钥重新生成一个对象加载到堆
优化措施：密钥文件设置成静态变量，密钥文件在项目启动时就加载在内存
此轮优化，qps提升两倍
### 第二轮优化
优化部署后，cpu问题好多了，但对于高峰期流量效果还是不理想
观察线程栈，发现本地缓存失效后，获取数据耗时长
另外观察到年轻代gc频繁，dump堆用mat进行分析，发现线程引用的jsonObject对象很多，查看线程调用栈，最后发现是jsonObject转化用的方法不合适，每次转化都要深拷贝
优化：增加后台定时任务更新本地缓存，保证本地缓存不失效；更改jsonObject转化方式
优化后，qps提升至3倍
### 第三轮优化
瞬时高峰流量不理想，还是会出现499情况
通过观察线程栈，最后定位到RSACore的一个方法，此方法中的缓存get其中value需要先获取锁，当并发量高的时候，就造成了阻塞
      
常用命令总结：
1. top ${pid} 命令：查看进程
2. top -Hp ${pid} 命令：查看进程中各个线程占用cpu的情况，以及进程开启的线程总数
3. jstack ${pid} 命令：查看线程堆栈信息
4. jstack ${pid} | grep 线程ID 命令： 查看具体哪一个线程堆栈信息
5. jstat -gcutil ${pid} 命令：查看进程垃圾堆使用情况，垃圾回收情况
6. jmap -dump:format=b,file=filename ${pid} 命令：将堆中的信息导出到mat文件进行分析
7. mat文件分析参考文档：https://juejin.cn/post/6911624328472133646
   https://blog.csdn.net/lyd135364/article/details/121449969
8. arthas 生成火焰图，看各个线程使用资源情况

以及json.formatJson，最后深度拷贝json，流量不高的情况，能被垃圾回收器回收，
当流量高峰，流量阻塞的时候，线程所引用的json不会释放掉，就会导致内存也飙升，执行效率更低




常用命令：
1. 查看CPU核数：
   cat /proc/cpuinfo | grep "cpu cores" | uniq
2. 查看内存大小：
   cat /proc/meminfo | grep MemTotal
3. 


