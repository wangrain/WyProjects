package kot.wy.algorithm.sort

/**
 * 名称：  SortValidation
 * 作者:   rain.wang
 * 日期:   2018/4/5
 * 简介:
 *
 */
class SortValidation {
    companion object {
        fun validate(list: ArrayList<Long>):Boolean{
            for(i in list.indices){
                if(i < list.size-1 && list[i] > list[i+1]){
                    return false
                }
            }
            return true
        }
    }
}