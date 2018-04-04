package kot.wy.algorithm.snowflake


/**
 * 名称：  SnowFlake
 * 作者:   rain.wang
 * 日期:   2018/4/4
 * 简介:
 *
 */
class SnowFlake {


    companion object {
        private val twepoch = 1288834974657L

        private val workerIdBits = 10L;
        private val sequenceBits = 12L;
        private val sequenceMask = -1L xor (-1L shl sequenceBits.toInt());
        private val timestampLeftShift = sequenceBits + workerIdBits + workerIdBits;
        private val workerIdShift = sequenceBits;


        private var workerId = 0L;
        private var sequence = 0L;
        private var lastTimestamp = -1L;
        public fun getId(workerId: Long): Long {

            var timestamp = System.currentTimeMillis();
            if (timestamp == lastTimestamp) {
                //当前毫秒数等于上次毫秒数，则序列号+1
                sequence = (sequence + 1) and sequenceMask;

                if (sequence == 0L) {
                    //若序列号归零，则等待下一毫秒
                    while (timestamp < lastTimestamp) {
                        timestamp = System.currentTimeMillis();
                    }
                }
            } else {
                sequence = 0;
            }

            lastTimestamp = timestamp;

            return timestamp - twepoch shl timestampLeftShift.toInt() or (workerId shl workerIdShift.toInt()) or sequence;
        }
    }

}


fun main(args: Array<String>) {

    for(x in 1..100L) {
        println(SnowFlake.getId(1L))
    }
}