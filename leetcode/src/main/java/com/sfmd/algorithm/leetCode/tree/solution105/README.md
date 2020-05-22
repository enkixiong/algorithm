##### 思路1

    前提: 
    1. 根据下标来标明子树的数据范围;
    2. 递归
    
    思路: 
    1. 根据先序遍历的第一个节点，获取父节点; 
    2. 根据父节点的值, 在中序遍历中寻找父节点下标 subTreeCenter；
    3. 根据父节点在中间节点的下标,获取左子树的大小: subTreeCenter - inOrderLeft:subLeftSize
    4. inOrderRight - (subCenter+1) 就是右子树的大小:subRightSize
    5. 先序遍历中, [1,1+subLeftSize) 是左子树先序遍历的
    6. 先序遍历中: [i+subLeftSize, preRight) 是右子树的先序遍历
    7. 中序遍历中: [inOrderLeft, subTreeCenter) 是左子树的中序遍历结果
    8. 中序遍历中: [subTreeCenter+1, inOrderRight) 是右子树中序遍历的结果
    9. 根据左子树的先序遍历, 中序遍历 结果,构造左子树；
    10.根据右子树的先序遍历, 中序遍历 结果,构造右子树;
    
    退出条件: 
    1. 当前先序遍历数组为空;则退出子树的构建;
    2.  