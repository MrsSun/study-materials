#Dubbo服务暴露的过程：
1. 先检查配置，组装URL
2. 创建Invoker，简单说就是具体执行操作对象的代理, 使用invoker使得调用简单，屏蔽底层实现细节。创建流程：通过getInvoker()方法创建wrapper，目标类的包装类；
3. 根据不同的protocol导出Exporter，protocol分为本地（InjvmProtocol）和远程（RegistryProtocol），也就是使用exporter进一步封装了invoker
   1. 本地比较简单，就是创建InjvmExporter
   2. 远程使用不同协议的protocol导出不同的exporter，启动服务
   3. 启动服务，监听端口
4. 服务注册：创建注册中心，创建节点；如果配置多个注册中心，会遍历向每个注册中心进行注册；

##最后实现的效果：
根据服务中心注册的URL（包含用户名/密码、主机/端口、接口path），找到exporter，根据exporter找到invoker，根据invoker最终找到具体的实现类。

