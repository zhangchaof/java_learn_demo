java 事件机制包括三个部分：事件、事件监听器、事件源。
1、事件。一般继承自 java.util.EventObject 类，封装了事件源对象及跟事件相关的信息。com.zhang.javase.learn.demo.event.CusEvent 类
2、事件监听器。实现 java.util.EventListener 接口,注册在事件源上,当事件源的属性或状态改变时,取得相应的监听器调用其内部的回调方法。
3、事件源。事件发生的地方，由于事件源的某项属性或状态发生了改变(比如 BUTTON 被单击、TEXTBOX 的值发生改变等等)导致某项事件发生。
换句话说就是生成了相应的事件对象。因为事件监听器要注册在事件源上,所以事件源类中应该要有盛装监听器的容器(List,Set等等)。