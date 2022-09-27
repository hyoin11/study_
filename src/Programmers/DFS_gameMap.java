package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DFS_gameMap {
    // https://school.programmers.co.kr/learn/courses/30/lessons/1844

    public static void main(String[] args) {
        try {
            DFS_gameMap obj = new DFS_gameMap();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        System.out.println(solution(maps));
    }

    public int solution(int[][] maps) {
        int[][] visited = new int[maps.length][maps[0].length];
        bfs(maps, visited);

        return visited[maps.length-1][maps[0].length-1] == 0 ? -1 : visited[maps.length-1][maps[0].length-1];
    }

    public static void bfs(int[][] maps, int[][] visited){
        Queue<int[]> que = new LinkedList<>();
        visited[0][0] = 1;
        que.add(new int[]{0, 0});

        int[][] arr = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!que.isEmpty()){
            int[] current = que.poll();
            int x = current[0];
            int y = current[1];

            for(int i=0; i<4; i++){
                int nX = x + arr[i][0];
                int nY = y + arr[i][1];

                if(nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1){
                    continue;
                }
                if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
                    visited[nX][nY] = visited[x][y] + 1;
                    que.add(new int[]{nX, nY});
                }
            }
        }
    }
}
