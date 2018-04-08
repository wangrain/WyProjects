package kot.wy.algorithm.sort

import kot.wy.algorithm.snowflake.SnowFlake
import java.util.*
import kotlin.collections.ArrayList


class QuicksortUtil {

    companion object {
        fun sort(array: ArrayList<Long>) :ArrayList<Long> {

            if(array.size==0) return array

            //选择第一个为锚点元素
            var index = 0

            //遍历锚点元素
            for (i in array.indices) {
                if (array[i] < array[index]) {
                    //当某个元素小于锚点，将它放到锚点左侧
                    array.add(index, array[i]);
                    array.removeAt(i + 1)
                    index++
                }
            }
            //当前列表锚点左侧为小于锚点的元素，右侧为大于锚点的元素

            val result = ArrayList<Long>(array.size)
            //通过迭代对锚点左侧列表排序
            if(index > 0) result.addAll(sort(listToArrayList(array.subList(0,index))))
            result.add(array[index])
            //通过迭代对锚点右侧列表排序
            if(index < array.size) result.addAll(sort(listToArrayList(array.subList(index+1,array.size))))
            return result
        }


    }
}

/**
 * 将List列表转化为ArrayList列表
 */
fun <T> listToArrayList (list: List<T>) :ArrayList<T>{
    if(list is ArrayList) return list

    val result = ArrayList<T>()
    for (l in list) result.add(l)
    return result
}


fun main(args: Array<String>) {

    val list =listToArrayList(Array(100000, { SnowFlake.getId(Random().nextInt().toLong()) }).asList())
//    val list = arrayListOf(-3291496448, 9165924150039621633, 9165924165898358786, 9165924179372584963, -553899446268, 9165924699732996101, 9165924196813336582, -2667028473, -591313469432, -4378206199)

    println("原list为" + (if (SortValidation.validate(list)) "有序的" else "无序的"))
//    println(list)
    val start = System.currentTimeMillis()
    val sortedList = QuicksortUtil.sort(list)
    println("排序${list.size}条数据用时${System.currentTimeMillis() - start}毫秒")
    println("排序后list为" + (if (SortValidation.validate(sortedList)) "有序的" else "无序的"))
//    println(sortedList)
}