package com.zljin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    private int val;
    private TreeNode left;
    private TreeNode right;
}
