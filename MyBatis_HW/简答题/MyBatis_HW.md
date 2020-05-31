# HW

1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？

   Mybatis动态sql可以让我们在Xml映射文件内，以标签的形式编写动态sql，完成逻辑判断和动态拼接sql的功能。
   Mybatis提供了9种动态sql标签<if/>、<choose/>、<when/>、<otherwise/>、<trim/>、<when/>、<set/>、<foreach/>、<bind/>
   其执行原理为，使用OGNL(Object Graph Navigation Language)从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能.

2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

   Mybatis 仅支持 association 关联对象和 collection 关联集合对象的延迟加载，association 指的就是一对一，collection 指的就是一对多查询。
   在 Mybatis配置文件中，可以配置是否启用延迟加载 lazyLoadingEnabled=true / false。
   它的原理是，使用 CGLIB(Code Generator Library) 创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，
   比如调用 a.getX().getCountry()，拦截器 invoke()方法发现 a.getX()是null 值，那么就会单独发送事先保存好的查询关联 X 对象的 sql，把 X 查询上来，
   然后调用 a.setX(X)，于是 a 的对象 X 属性就有值了，接着完成 a.getX().getCountry()方法的调用。这就是延迟加载的基本原理。

3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？

   SimpleExecutor、ReuseExecutor、BatchExecutor。
   SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
   ReuseExecutor： 执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。简言之，就是重复使用Statement对象。
   BatchExecutor： 执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。
   作用范围：Executor的这些特点，都严格限制在SqlSession生命周期范围内。

4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？

   MyBatis默认开启一级缓存，MyBatis的一级缓存是基于SqlSession级别的，也就是说一级缓存的生命周期和SqlSession相同。
   MyBatis自己实现了一套本地缓存，在cache包下，有个默认实现PerpetualCache，该缓存是通过HashMap实现的，DefaultSqlSession拥有几个属性，它将缓存属性放在Executor这个属性里面封装了起来。
   某个SqlSession进行某个查询后会将该结果放在自己的Map对象中缓存起来。如果在之后所有的查询操作到来之前有任意的SqlSession对该表进行插入、修改、删除写操作，就会清空这个Map中所有缓存信息，重复第一次查询过程；
   否则当这SqlSession再次发起查询时将先从缓存中获取结果，在缓存中没有获取到结果的情况下才会进行连库查询。
   MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象，SqlSession对象中会有一个新的Executor对象。Executor对象中持有一个新的PerpetualCache对象；当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。
   如果SqlSession调用了close()方法，会释放掉一级缓存PerpetualCache对象，一级缓存将不可用。
   如果SqlSession调用了clearCache()，会清空PerpetualCache对象中的数据，但是该对象仍可使用。
   SqlSession中执行了任何一个update操作(update()、delete()、insert()) ，都会清空PerpetualCache对象的数据，但是该对象可以继续使用
   
   MyBatis的二级缓存是基于Mapper级别的，也就是说多个SqlSession去使用某个Mapper的查询语句时，得到的缓存数据是可共用的。
   二级缓存开启后，查询就会走二级缓存，没查到直接查库，不存在没查到先走一级缓存这种情况。
   MyBatis默认不开启二级缓存，要使用二级缓存需要做到三点：
   在MyBatis配置中指定cache-anable属性为true
   在需要缓存的Mapper.xml文件中添加<cache />标签，指定type缓存实现PerpetualCache，缓存丢弃策略eviction（LRU、FIFO等），刷新时间flushInterval等
   让使用二级缓存的POJO类实现Serializable接口
   映射语句文件中的所有select语句将会被缓存。
   映射语句文件中的所有insert、update和delete语句会刷新缓存。
   缓存会使用默认的Least Recently Used（LRU，最近最少使用的）算法来收回。
   根据时间表，比如No Flush Interval,（CNFI没有刷新间隔），缓存不会以任何时间顺序来刷新。
   缓存会存储列表集合或对象(无论查询方法返回什么)的1024个引用
   缓存会被视为是read/write(可读/可写)的缓存，意味着对象检索不是共享的，而且可以安全的被调用者修改，不干扰其他调用者或线程所做的潜在修改。

5、简述Mybatis的插件运行原理，以及如何编写一个插件？

   Mybatis 仅可以编写针对 ParameterHandler、ResultSetHandler、StatementHandler、Executor 这4大核心对象的插件，
   Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，
   具体就是InvocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。
   
   实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，还要在配置文件中配置你编写的插件。
   如果多个插件对一个对象的方法进行拦截 就会产生层层拦截效果 第一个对象的动态代理对象传给第二个插件包装,
   当执行目标方法的时候先调用第二个插件的intercept 然后调用第一个插件的intercept方法 最后执行真正的目标方法.
   创建动态代理的时候 是按照插件配置顺序创建层层代理对象,执行目标方法，是按逆向顺序执行的.
