from sys import setrecursionlimit
setrecursionlimit(10000)
class Node: 
    def __init__(self,key,value,left,right):
        self.key=key #키 
        self.value=value # 값
        self.left=left # 왼쪽 자식
        self.right=right #오른쪽 자식
        
        
class BinaryTree():
    def __init__(self) -> None: #트리 생성
        self.root = None
        self.order_result=[[],[]]

    def get_order_result(self):
        return self.order_result
    
    def add(self,key,value) -> bool: #노드 추가 메소드
        def add_node(node,key,value)-> None :
            #이미 삽입하려는 키가 있으면 false처리
            if key== node.key :
                return False
            #삽입하려는 키가 현재 탐색 노드의 키보다 작다면    
            elif key < node.key : 
                #그 탐색 노드의 왼쪽 자식이 없다면 바로 그 자리에 삽입
                if node.left is None:
                    node.left = Node(key,value,None,None)
                else:
                    #자식이 있다면 재귀 함수 호출로 한번 더 들어감
                    add_node(node.left,key,value)
            #삽입하려는 키가 현재 탐색 노드의 키보다 크다면 
            else:
                # 그 탐색 노드의 오른쪽 자식이 없다면 바로 그 자리에 삽입
                if node.right is None:
                    node.right = Node(key,value,None,None)
                else:
                    #자식이 있다면 재귀함수 호출로 한번 더 들어감
                    add_node(node.right,key,value)
            
        #루트가 없는 경우
        if self.root is None:
            self.root = Node(key,value,None,None)
            return True
        #루트가 없는 경우
        else : 
            return add_node(self.root,key,value)
        
    # 노드 전위 순회로 출력하는 메소드
    def make_preorder(self):
        def pre_order(node):
            # 전위 순회로 출력
            if node is not None:
                #print(f'{node.value}')
                self.order_result[0].append(node.value)
                pre_order(node.left)
                pre_order(node.right)
        root = self.root
        pre_order(root)
        
    # 노드 후위 순회로 출력하는 메소드
    def make_postorder(self):
        def post_order(node):
            # 후위 순회로 출력
            if node is not None:
                post_order(node.left)
                post_order(node.right)
                #print(f'{node.value}')
                self.order_result[1].append(node.value)
        root = self.root
        post_order(root)
        
def solution(nodeinfo):
    nodeinfo = refactor_nodeinfo(nodeinfo)
    sort_y(nodeinfo)
    
    binaryTree = BinaryTree()
    for node in nodeinfo:
        binaryTree.add(node[0],node[2]) #x값이 key, z값이 value
    
    binaryTree.make_preorder()
    binaryTree.make_postorder()
    answer = binaryTree.get_order_result()
    return answer

def refactor_nodeinfo(nodeinfo):
    for i in range(1,len(nodeinfo)+1):
        nodeinfo[i-1].append(i)
    return nodeinfo

def sort_y(nodeinfo):
    nodeinfo.sort(key = lambda nodeinfo: -nodeinfo[1])
    return nodeinfo
