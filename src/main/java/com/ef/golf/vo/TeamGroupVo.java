package com.ef.golf.vo;

import java.util.List;

/**
 * 球队小组
 * Created by Administrator on 2018/9/19.
 */
public class TeamGroupVo {


    private String teamMemberName;//组员配对名字

    private String totalScore;//组员总积分

    private String finishHoleNum;//已打洞数

    private String totalBarNum;//总杆数

    private List<HoleScoreVo> outHoleScore;//前9洞成绩

    private List<HoleScoreVo> inHoleScore;//后9洞成绩
}
