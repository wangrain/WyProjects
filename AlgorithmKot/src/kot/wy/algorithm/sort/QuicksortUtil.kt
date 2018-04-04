package kot.wy.algorithm.sort

import kot.wy.algorithm.snowflake.SnowFlake
import java.util.*


class QuicksortUtil {

    companion object {
        fun sort(array: ArrayList<Long>) :ArrayList<Long> {
            var index = 0;
            for (i in array.indices) {
                if (array[i] < array[index]) {
                    array.add(index, array[i]);
                    array.removeAt(i + 1);
                    index++;
                }
            }

            if(index ==0) return array;

            val result = ArrayList<Long>(array.size)
            result.addAll(sort(subToArrayList(array.subList(0,index))))
            result.add(array[index])
            result.addAll(sort(subToArrayList(array.subList(index+1,array.size))))
            return result
        }


    }
}

fun <T> arrayToArrayList (array: Array<T>) :ArrayList<T>{
    var list = ArrayList<T>(array.size)
    for(t in array) list.add(t)
    return list
}

fun <T> subToArrayList (array: List<T>) :ArrayList<T>{
    var list = ArrayList<T>()
    for(t in array) list.add(t)
    return list
}


fun main(args: Array<String>) {

    val list = arrayToArrayList(Array(10,{SnowFlake.getId(Random().nextInt().toLong())}))

    println(list)
    println(QuicksortUtil.sort(list))
}