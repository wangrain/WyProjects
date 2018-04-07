package kot.wy.algorithm.sort

import kot.wy.algorithm.snowflake.SnowFlake
import java.util.*


class QuicksortUtil {

    companion object {
        fun sort(array: ArrayList<Long>) :ArrayList<Long> {

            if(array.size==0) return array
            var index = 0

            for (i in array.indices) {
                if (array[i] < array[index]) {
                    array.add(index, array[i]);
                    array.removeAt(i + 1)
                    index++
                }
            }

            val result = ArrayList<Long>(array.size)
            if(index > 0) result.addAll(sort(listToArrayList(array.subList(0,index))))
            result.add(array[index])
            result.addAll(sort(listToArrayList(array.subList(index+1,array.size))))
            return result
        }


    }
}

fun <T> listToArrayList (list: List<T>) :ArrayList<T>{
    val result = ArrayList<T>()
    for (l in list) result.add(l)
    return result
}


fun main(args: Array<String>) {

    val list =listToArrayList(Array(100, { SnowFlake.getId(Random().nextInt().toLong()) }).asList())
//    val list = arrayListOf(-3291496448, 9165924150039621633, 9165924165898358786, 9165924179372584963, -553899446268, 9165924699732996101, 9165924196813336582, -2667028473, -591313469432, -4378206199)

    println("原list为" + (if (SortValidation.validate(list)) "有序的" else "无序的"))
    println(list)
    val sortedList = QuicksortUtil.sort(list)
    println("排序后list为" + (if (SortValidation.validate(sortedList)) "有序的" else "无序的"))
    println(sortedList)
}