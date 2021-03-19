package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/19 9:20
 * @Motto Keep thinking, keep coding!
 * leetcode 1603、设计停车系统
 * 题目：https://leetcode-cn.com/problems/design-parking-system/
 */
public class Leetcode1603_ParkingSystem {

}

/*
class ParkingSystem {

    int[] parking = new int[3];

    public ParkingSystem(int big, int medium, int small) {
        parking[0] = big;
        parking[1] = medium;
        parking[2] = small;
    }

    public boolean addCar(int carType) {
        parking[carType - 1] -= 1;
        return parking[carType - 1] >= 0;
    }
}
*/

class ParkingSystem {

    int first;
    int second;
    int third;

    public ParkingSystem(int big, int medium, int small) {
        first = big;
        second = medium;
        third = small;
    }

    public boolean addCar(int carType) {
        int x;
        if (carType == 1) {
            first -= 1;
            x = first;
        } else if (carType == 2) {
            second -= 1;
            x = second;
        } else {
            third -= 1;
            x = third;
        }
        return x >= 0;
    }
}