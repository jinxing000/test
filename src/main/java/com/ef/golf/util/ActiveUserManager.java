package com.ef.golf.util;

import java.util.Random;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:11
 */
public class ActiveUserManager {


    private int groupId = 0; // 默认不分组
    private int serverId = 0; // 默认不分server
    private static Random random = new Random(); //用来生成唯一sessionID的随机对象
    private static short sequeceId = 0; // 用来生成sessionID

    private static ActiveUserManager activeUserManager;

    public static ActiveUserManager getInstance(){
        if(activeUserManager==null)
            activeUserManager = new ActiveUserManager();
        return activeUserManager;
    }


    private static String createSessionID(int groupId, int serverId,
                                          short sequeceId) {
        if (0 == groupId && 0 == serverId) {
            long t1 = 0x000000007FFFFFFF & System.currentTimeMillis();

            return Long.toString(((t1 << 32) | Math.abs(random.nextInt())));
        }

        if (groupId < 0 || groupId > 127 // 1个字节的正数最大值是127
                || serverId < 0 || serverId > 255) { // 允许负数
            return null;
        }

        long p1 = ((long)groupId << 56) & 0x7F00000000000000L; // 第1字节表示groupId
        long p2 = ((long)serverId << 48) & 0x00FF000000000000L; // 第2字节表示serverId
        long p3 = ((0xFFFFFFFF & (System.currentTimeMillis() / 1000)) << 16)
                & 0x0000FFFFFFFF0000L; // 时间占4字节(取秒)
        long p4 = sequeceId & 0x000000000000FFFFL; // 序号占2字节

        long sessionID = p1 | p2 | p3 | p4;

        return Long.toString(sessionID);
    }

    public String createSessionID() {
        short localSequenceId;
        synchronized (ActiveUserManager.class) {
            ++sequeceId;
            localSequenceId = sequeceId;
        }
        return createSessionID(groupId, serverId, localSequenceId);
    }

}
