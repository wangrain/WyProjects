package kot.wy.algorithm.snowflake


/**
 * 名称：  SnowFlake
 * 作者:   rain.wang
 * 日期:   2018/4/4
 * 简介:  雪花算法，ID生成器：
            |-----------------41bit时间戳------------------|                |-12bit序列号-|
        0 - 00000000 00000000 00000000 00000000 00000000 0 - 00000000 00 - 00000000 0000
|1bit不用|                                                   |10bit机器ID|

         实际生成40bit的ID，长度为18
 */
class SnowFlake {


    companion object {
        //默认开始纪元为 格林威治时间1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)
        //开始纪元： 当前毫秒 - 开始纪元 = 时间戳——从开始纪元到当前时间的毫秒数，为0时从
        private const val wyNum = 632016000000L

        //workerId 占10位 最大1024
        private const val workerIdBits = 10
        //sequence 占12位 最大4096 (每毫秒每台机器)
        private const val sequenceBits = 12
        //得到4095 ： 0000000000000000000000000000000000000000000000000000111111111111，通过 & 填充
        private const val sequenceMask = -1L xor (-1L shl sequenceBits)
        //时间戳需要左移12+10=22位
        private const val timestampLeftShift = sequenceBits + workerIdBits
        //机器ID需要左移12位
        private const val workerIdShift = sequenceBits

        //序列号从0开始
        private var sequence = 0L
        //上次毫秒数 起始为 -1
        private var lastTimestamp = -1L
        fun getId(workerId: Long): Long {

            //获取当前毫秒数
            var timestamp = System.currentTimeMillis()

            if (timestamp == lastTimestamp) {
                //当前毫秒数等于上次毫秒数，则序列号+1
                sequence = (sequence + 1)

                if (sequence == 0L) {
                    //若序列号归零，则等待下一毫秒
                    while (timestamp < lastTimestamp) {
                        timestamp = System.currentTimeMillis()
                    }
                }
            } else {
                sequence = 0
            }

            lastTimestamp = timestamp

            //当前毫秒数 - 开始纪元 = 时间戳，左移22位获得时间戳的比特值
            val timestampBitsValue = timestamp - wyNum shl timestampLeftShift
            //机器ID左移12位，得到机器ID比特值
            val workerIdBitsValue = workerId shl workerIdShift
            //序列号和4095(12位1)进行 & 按位与运算后，得到12位序列号的比特值
            val sequenceBitsValue = sequence and sequenceMask

            //通过 | 按位或运算拼接三个比特值，获取最终唯一值
            return timestampBitsValue or workerIdBitsValue or sequenceBitsValue
        }
    }

}


fun main(args: Array<String>) {

    for(x in 1..10L) {
        println(SnowFlake.getId(1L))
    }
}