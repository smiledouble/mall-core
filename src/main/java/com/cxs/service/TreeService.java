package com.cxs.service;


import com.cxs.model.TreeNode;

import java.util.List;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/25 10:15
 */
public interface TreeService {

    /**
     * 懒加载树
     * 该节点里面不包含 子节点
     */
    List<TreeNode> loadTreeByParent(Integer pid);

    /**
     * 全局加载
     * 该节点里面包含子节点
     *
     * @return
     */
    List<TreeNode> loadAllTree();

    /**
     * 给树增加一个节点
     *
     * @param node 树节点的数据
     * @return 影响的行数
     */
    int addTreeNode(TreeNode node);

    /**
     * 删除该树节点
     * 该节点为子节点，可能影响父节点
     * 该节点为父节点，可能影响子节点
     *
     * @param id
     * @return
     */
    int deleteNode(Integer id);

    /**
     * 修改该树的节点
     *
     * @param node
     * @return
     */
    int updateNode(TreeNode node);


}
