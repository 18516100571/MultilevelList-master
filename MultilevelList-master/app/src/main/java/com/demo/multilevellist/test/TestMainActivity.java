package com.demo.multilevellist.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.demo.multilevellist.R;
import com.demo.multilevellist.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestMainActivity extends AppCompatActivity {

    private TestTreeAdapter adapter;
    private ListView listView;
    private List<TestTreePoint> showList = new ArrayList<>();
    private HashMap<String, TestTreePoint> pointMap = new HashMap<>();
    /**
     * 跟节点list
     */
    private List<TestTreePoint> genList = new ArrayList<>();

    /**
     * id
     *
     * @param savedInstanceState
     */
    private int ID = 1000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        init();
        addListener();
    }


    public void setContentView() {
        setContentView(R.layout.activity_main);
    }


    public void init() {
        adapter = new TestTreeAdapter(this, showList, pointMap);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        getGenList();
    }

    private void getGenList() {
        showList.clear();
        for (int i = 0; i < 5; i++) {
            ID++;
            TestTreePoint tre = new TestTreePoint(ID + "", "跟节点" + i, "0", i, false, 0, true);
            pointMap.put("" + ID, tre);
            genList.add(tre);
            showList.add(tre);
        }
        adapter.setPointMap(pointMap);
        adapter.setPointList(showList);

        adapter.notifyDataSetChanged();
    }

    public void addListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setEnabled(false);
                listView.setClickable(false);
                getSubList(position, showList.get(position));
            }
        });
    }

    final List<TestTreePoint> subList = new ArrayList<>();

    private void getSubList(final int position, final TestTreePoint treePoint) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (treePoint.isExpand()) {

                } else {
                    try {


                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /**
                 * 模拟取子节点数据
                 */

                subList.clear();
                for (int h = 0; h < 3; h++) {
                    ID++;
                    subList.add(new TestTreePoint(ID + "", "子节点" + h, treePoint.getID(), h, false, 0, true));
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setEnabled(true);
                        listView.setClickable(true);
                        if (1 == 2) {//无数据
                            return;
                        } else if (subList.size() > 0 && !treePoint.isExpand()) {//点击项有子数据但未展开，展开操作。
                            for (int i = 0; i < subList.size(); i++) {
                                pointMap.put(subList.get(i).getID(), subList.get(i));
                                ID++;
                                genList.add(new TestTreePoint(subList.get(i).getID(), getSubmitResult(subList.get(i)), treePoint.getID(), i, false, getLayer(treePoint) + 1, true));
                            }
                            int g = position;
                            for (int j = 1; j < genList.size(); j++) {
                                if (genList.get(j).getPARENTID().equals(treePoint.getID())) {
                                    g = g + 1;
                                    showList.add(g, genList.get(j));
                                }
                            }
                            showList.get(position).setExpand(true);
                            adapter.setPointMap(pointMap);
                            adapter.setPointList(showList);
                            adapter.notifyDataSetChanged();
                        } else if (subList.size() > 0 && treePoint.isExpand()) {
                            List<TestTreePoint> delList = new ArrayList<>();
                            for (int k = position + 1; k < showList.size(); k++) {
                                if (showList.get(k).getLayer() > treePoint.getLayer()) {
                                    delList.add(showList.get(k));
                                } else {
                                    break;
                                }
                            }
                            genList.removeAll(delList);
                            showList.removeAll(delList);
                            for (TestTreePoint rr : delList) {
                                pointMap.remove(rr.getID());
                            }
                            showList.get(position).setExpand(false);
                            adapter.setPointMap(pointMap);
                            adapter.setPointList(showList);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });


            }
        }).start();


    }

    private int getLayer(TestTreePoint treePoint) {
        return TreeUtils.getLevel(treePoint, pointMap);
    }

    private String getSubmitResult(TestTreePoint treePoint) {
        StringBuilder sb = new StringBuilder();
        addResult(treePoint, sb);
        String result = sb.toString();
        if (result.endsWith("-")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private void addResult(TestTreePoint treePoint, StringBuilder sb) {
        if (treePoint != null && sb != null) {
            sb.insert(0, treePoint.getNNAME() + "-");
            if (!"0".equals(treePoint.getPARENTID())) {
                addResult(pointMap.get(treePoint.getPARENTID()), sb);
            }
        }
    }

}
