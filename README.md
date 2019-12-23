# hydosky-send
**环境**：mysql 5.7及以上、jdk 1.8

**项目结构**：

hydosky-send     # 项目名

- doc      #   存放sql脚本
- src.main
  - java.org.hydosky.send
    - config                     #  相关配置项
    - constant                 # 系统常量池
    - controller               #  接口
    - data                         #  生成订单 及 统计数据
    - entity                       #  实体类
    - mapper                   # 持久化层
    - response                 # 响应体
    - schedule                 # 定期删除 随时间增长的数据
    - service                     # 业务层 
    - util                            # 工具类
    - vo                              # 视图类
  - resources                 # 配置文件
## 1. 使用步骤

### 1.1 克隆或下载项目

点击Clone or download，选择项目**git地址**克隆项目到本地，或点击Download ZIP，下载一份到本地。然后使用idea或者eclipse打开项目。

### 1.2 执行sql脚本

将项目根目录里**doc**文件夹中的**sql脚本**导入到数据库中。里面是所有的**表结构**，以及**初始化的数据**。

### 1.3 修改配置

- 根据**环境**修改**application.yml**文件中的**数据库地址**，以及**用户名**和**密码**。
- 根据自己的需求修改项目的**访问端口**，我指定的是8108。
- resources文件下的**logback-spring.xml**文件中可指定日志的存活时间。我指定的是5天。

### 1.4 本地打包

可以本地运行，测试一下。也可以直接执行maven打包语句，将jar包上传到服务器执行。

### 1.5 执行jar包

使用 java -jar 包名 执行jar包，当然你也可以指定项目需要的内存大小，以及jvm参数。这里有两点要注意一下，首先要指定所使用的环境，命令是-Dspring.profiles.active=prod，这样就会加载生产环境application-prod.yml的配置，第二点是指定保存日志的路径，命令是-Dlog.basicDir=路径。

你也可以使用下面的方式，脚本我已经写好了，你按照我的方式来就行了。**当然，嫌下面的方式麻烦的话，你可以跳过了**，按照自己的方式来，Follow your heart！

1.  /home/username（此目录自己选择）下创建相应的目录及文件，username 为你登录用户名。

   目录结构如下：
   /home/username
   
      - -- hydosky-send
   
          - -- logs         # 日志存放目录，定期清除，不用担心磁盘爆满
   
          - -- shell      			# 脚本存放目录
   
               - pid.txt        # 此文件主要存放进程的pid。再次启动时，停掉正在运行的，防止端口冲突
               - hydosky-send-boot.sh          # 启动脚本  
   
          -  -- jars        			# jar包存放目录
   
               - hydosky-send.jar


2. hydosky-send-boot.sh脚本内容

   ```shell
   #! /bin/sh
   #SERVICE_HOME就是上面的/home/username/hydosky-send目录
   SERVICE_HOME="/home/username/hydosky-send"
   SERVICE_PID=`cat /home/username/hydosky-send/shell/pid.txt`
   
   echo "停掉正在运行的hydosky-send后台进程，PID=${SERVICE_PID}"
   kill -9 $SERVICE_PID
   echo "PID=${SERVICE_PID}的服务已被kill"
   
   echo "hydosky-send服务重新启动"
   java -Xms128m -Xmx128m -Xmn56m -XX:MetaspaceSize=80m -XX:MaxDirectMemorySize=20m -Duser.timezone=Asia/Shanghai -Djava.security.edg=file:/dev/./urandom -Dlog.basicDir=${SERVICE_HOME}/logs -Dspring.profiles.active=prod -jar ${SERVICE_HOME}/jars/hydosky-send.jar &
   #将本次运行的服务进程PID记录到pid.txt中
   echo $!> ${SERVICE_HOME}/shell/pid.txt
   echo "hydosky-send服务启动成功"
   ```

3.  执行hydosky-send-boot.sh

```shell
sh /home/username/hydosky-send/shell/hydosky-send-boot.sh
```

### 1.6 修改参数，动态改变数据

修改城市系数、营业时间、基准城市的日订单量、订单运费区间、每单获取件数区间，以及增加新的城市，都可动态的改变订单的生成效果，即可达到统计数据的变化。



## 2. 大屏展示数据接口及响应格式

注：统一为GET请求

### 2.1 当日实时热门货物品类

**Path :** **/hydosky-send/statistics/date-hot-type**

**响应数据格式**：

```json
[
    {
        "date": "2019-12-22",
        "type": "箱包皮具",
        "orderCount": 1
    }
]
```



### 2.2 实时热门货运城市

**Path :** **/hydosky-send/statistics/hot-city**

**响应数据格式**：

（以下数据为初始化数据）

```json
[
    {
        "name": "兰州",
        "lng": 103.823557,
        "lat": 36.058039,
        "orderCount": 21121,
        "zoom": 1.129
    },
    {
        "name": "柳州",
        "lng": 109.411703,
        "lat": 24.314617,
        "orderCount": 19626,
        "zoom": 1.049
    },
    {
        "name": "济宁",
        "lng": 116.587245,
        "lat": 35.415393,
        "orderCount": 10280,
        "zoom": 0.549
    },
    {
        "name": "绵阳",
        "lng": 104.741722,
        "lat": 31.46402,
        "orderCount": 87102,
        "zoom": 4.659
    },
    {
        "name": "菏泽",
        "lng": 115.469381,
        "lat": 35.246531,
        "orderCount": 1495,
        "zoom": 0.079
    },
    {
        "name": "衡阳",
        "lng": 112.607693,
        "lat": 26.900358,
        "orderCount": 2056,
        "zoom": 0.109
    },
    {
        "name": "赣州",
        "lng": 114.940278,
        "lat": 25.85097,
        "orderCount": 31962,
        "zoom": 1.709
    },
    {
        "name": "长沙",
        "lng": 112.982279,
        "lat": 28.19409,
        "orderCount": 12710,
        "zoom": 0.679
    }
]
```



### 2.3 今日订单总量、订单总额、货物总量

**Path :** **/hydosky-send/statistics/date-statistics**

**响应数据格式**：

```json
[
    {
        "今日订单总量": "3笔",
        "今日订单总额": "56元",
        "今日货物总量": "8件"
    }
]
```



### 2.4 历史订单总量、订单总额、货物总量

**Path :** **/hydosky-send/statistics/history-statistics**

**响应数据格式**：

（以下数据为初始化数据）

```json
[
    {
        "历史订单总量": "186915笔",
        "历史订单总额": "3738309元",
        "历史货物总量": "560764件"
    }
]
```



### 2.5 城市货运金额统计

**Path :** **/hydosky-send/statistics/city-price**

**响应数据格式**：

（以下数据为初始化数据）

```json
[
    {
        "x": "绵阳",
        "y": 1742040,
        "s": null
    },
    {
        "x": "赣州",
        "y": 639240,
        "s": null
    },
    {
        "x": "兰州",
        "y": 422420,
        "s": null
    },
    {
        "x": "柳州",
        "y": 392520,
        "s": null
    },
    {
        "x": "长沙",
        "y": 254200,
        "s": null
    },
    {
        "x": "济宁",
        "y": 205600,
        "s": null
    },
    {
        "x": "衡阳",
        "y": 41120,
        "s": null
    },
    {
        "x": "菏泽",
        "y": 29900,
        "s": null
    }
]
```



### 2.6  城市货运数量统计

**Path :** **/hydosky-send/statistics/city-goods-count**

**响应数据格式**：

（以下数据为初始化数据）

```json
[
    {
        "x": "绵阳",
        "y": 261306,
        "s": null
    },
    {
        "x": "赣州",
        "y": 95886,
        "s": null
    },
    {
        "x": "兰州",
        "y": 63363,
        "s": null
    },
    {
        "x": "柳州",
        "y": 58878,
        "s": null
    },
    {
        "x": "长沙",
        "y": 38130,
        "s": null
    },
    {
        "x": "济宁",
        "y": 30840,
        "s": null
    },
    {
        "x": "衡阳",
        "y": 6168,
        "s": null
    },
    {
        "x": "菏泽",
        "y": 4485,
        "s": null
    }
]
```



### 2.7 全网货运品类排行

**Path :** **/hydosky-send/statistics/hot-category**

**响应数据格式**：

（以下数据为初始化数据）

```json
[
    {
        "x": "五金机电",
        "y": 79852,
        "s": null
    },
    {
        "x": "建材家居",
        "y": 31962,
        "s": null
    },
    {
        "x": "汽摩配件",
        "y": 28421,
        "s": null
    },
    {
        "x": "日用百货",
        "y": 21222,
        "s": null
    },
    {
        "x": "服装鞋包",
        "y": 12892,
        "s": null
    },
    {
        "x": "农资农机",
        "y": 11892,
        "s": null
    },
    {
        "x": "粮油副食",
        "y": 10821,
        "s": null
    }
]
```



### 2.8 近期订单金额统计

**Path :** **/hydosky-send/statistics/week-price-trend**

**响应数据格式**：

（以下数据为初始化数据）

```json
[
    {
        "x": "12.16",
        "y": 396620,
        "s": 1
    },
    {
        "x": "12.09",
        "y": 416764,
        "s": 1
    },
    {
        "x": "12.02",
        "y": 396764,
        "s": 1
    },
    {
        "x": "11.25",
        "y": 426764,
        "s": 1
    },
    {
        "x": "11.18",
        "y": 476734,
        "s": 1
    },
    {
        "x": "11.11",
        "y": 456734,
        "s": 1
    },
    {
        "x": "11.04",
        "y": 432354,
        "s": 1
    },
    {
        "x": "10.28",
        "y": 389943,
        "s": 1
    }
]
```



## 3. 设计思路

### 3.1 要求

基本要求：1. **各城市的订单数量同城市系数（权重）成正比**；2. **以赣州的数据为基准，并且城市系数可配置，修改后，数据动态变化**；3. **数据在一个可控范围内，不用人为的去维护数据库磁盘占用**。根据以上要求，于是我有了以下思路：

- 提供基本数据表，用于动态调整数据
- 根据基准数据以及城市系数得到每天应当生成的总订单数，再同每天的营业时间总毫秒数计算得到生成订单的间隔时间
- 城市系数就是城市每天生成的订单量的概率，大的每天的订单量多，由此可区分出热门城市和那些不温不火的城市
- 生成订单后，直接统计数据，根据无则插入、有则更新的sql去更新数据
- 对于随时间记录增长的数据表，每天凌晨2:00清除指定天数前的数据

### 3.2 设计

#### 3.2.1 表设计

这里只介绍与订单生成相关的表，即基本数据表、城市表。

1. 基本数据表(base_data)结构：

| 字段名称          | 数据类型    | 备注                                           |
| ----------------- | ----------- | ---------------------------------------------- |
| city_name         | varchar(20) | 城市名称。基准城市，初始化的是赣州             |
| day_order_count   | int(11)     | 日订单量。基准城市的日订单量，初始化的是110    |
| start_time        | varchar(5)  | 开始营业时间。初始化的是9:00                   |
| end_time          | varchar(5)  | 结束营业时间。初始化的是18:00                  |
| days              | int(3)      | 定期删除days天谴的数据，针对随时间记录增长的表 |
| goods_count_scope | varchar(8)  | 每单货物件数的区间，使用:分割。初始化的是2:4   |
| price_scope       | varchar(8)  | 每单运费区间，使用:分割。初始化的是18:22       |

2. 城市表(city)结构：

| 字段名称    | 数据类型      | 备注                           |
| ----------- | ------------- | ------------------------------ |
| name        | varchar(20)   | 城市名称                       |
| lng         | decimal(10,7) | 经度                           |
| lat         | decimal(10,7) | 纬度                           |
| order_count | bigint(20)    | 城市订单总量。用于热门城市接口 |
| weight      | decimal(4,3)  | 城市系数                       |

初始化数据：

| name | lng  | lat  | order_count | weight |
| ---- | ---- | ---- | ----------- | ------ |
| 兰州   | 103.8235570 | 36.0580390 |           0 |  0.113 |
| 柳州   | 109.4117030 | 24.3146170 |          0 |  0.105 |
| 济宁   | 116.5872450 | 35.4153930 |          0 |  0.055 |
| 绵阳   | 104.7417220 | 31.4640200 |          0 |  0.466 |
| 菏泽   | 115.4693810 | 35.2465310 |           0 |  0.008 |
| 衡阳   | 112.6076930 | 26.9003580 |           0 |  0.011 |
| 赣州   | 114.9402780 | 25.8509700 |          0 |  0.171 |
| 长沙   | 112.9822790 | 28.1940900 |           0 |  0.068 |



#### 3.2.2 核心功能设计

##### 3.2.2.1 订单生成的间隔时间计算

- 从base_data表中获取基准城市名称和日订单量
- 根据基准城市名称获取到此城市的城市系数，及所有的城市系数之和
- 计算得到每天应当生成的总订单量
- 根据营业开始时间和营业结束时间计算得到每天的营业总毫秒数
- 用每天的营业总毫秒数除以日总订单量，即可得到订单生成程序的睡眠时间

具体算法如下：

```java
/**
     * 根据赣州的日订单量、城市系数，以及所有城市系数之和 得到当日应当生成的订单数量，
     * 然后使用 日营业时间总毫秒数 除以 订单数量 ，得到订单生成程序睡眠时间。
     *
     * 当日应当生成的订单数量 = (赣州的日订单量 / 赣州的城市系数) * 城市系数之和
     * 睡眠时间 = 当日营业时间总毫秒数 / 当日应当生成的订单数量
     *
     * @param weights 所有城市系数之和
     * @return 程序睡眠时间
     */
    private long getSleepTime(double weights) {
        long sleepTime;
        // 查询赣州基准数据
        BaseData baseData = baseDataService.getOne(Wrappers.<BaseData>lambdaQuery().last("limit 1"));
        // 获取赣州城市数据
        City cityBase = cityService.getOne(Wrappers.lambdaQuery(City.of(baseData.getCityName())));
        Double cityBaseWeight = cityBase.getWeight().doubleValue();
        // 使用赣州日订单量除以赣州的城市系数，得到一个正整数
        int intBase = (int) (baseData.getDayOrderCount() / cityBaseWeight);

        // 得到当日应当生成的总订单数量
        int dayOrderCount = (int) (intBase * weights);
        // 得到营业时间总毫秒数
        long mills = DateUtils.millsBetween(baseData.getStartTime(), baseData.getEndTime());
        //  当日营业总毫秒数 除以 当日总订单量 得到生成订单间隔时间
        sleepTime = mills / dayOrderCount ;
        return sleepTime;
    }
```

**注意**：修改其他参数都可改变睡眠时间的大小。

##### 3.2.2.2 根据城市系数非均匀随机选择城市

城市系数大的城市每天生成的订单量多。具体算法如下：

```java
/**
     * 根据 城市系数 非均匀随机 选择城市
     *
     * 城市系数大的属于热门城市，
     * 当天生成的订单数量多于城市系数小的
     * @param cityList 城市列表
     * @param weights 城市系数之和
     * @return cityName 城市名称
     */
    private String getCityName(List<City> cityList, double weights) {
        String weightsStr = String.valueOf(weights);
        String weightsIntStr = weightsStr.substring(0, weightsStr.indexOf("."))
                + weightsStr.substring(weightsStr.indexOf(".") + 1, weightsStr.indexOf(".") + 4);
        int weightsInt = Integer.parseInt(weightsIntStr);
        int randomInt = random.nextInt(weightsInt);//randomInt in [0, weightsInt]
        int m = 0;
        String cityName = null;
        // 比较randomInt和城市系数，定位城市
        for (City city : cityList) {
            String weightStr = city.getWeight().toString();
            String weightIntStr = weightStr.substring(0, weightStr.indexOf("."))
                    + weightStr.substring(weightStr.indexOf(".") + 1 , weightStr.indexOf(".") + 4);
            int weightInt = Integer.parseInt(weightIntStr);
            if (m <= randomInt && randomInt < m + weightInt) {
                cityName = city.getName();
                break;
            }
            m += weightInt;
        }
        return cityName;
    }
```

##### 3.2.2.3 近期订单金额统计

此接口对应的图表是一个折线图，x轴是每周周一的日期，y周是周订单金额。

统计此数据的时候，我按照每周周一的日期保存数据。将当日所在周的周一日期得到，然后更新此周一日期的金额字段。

具体操作，请移步代码内。

