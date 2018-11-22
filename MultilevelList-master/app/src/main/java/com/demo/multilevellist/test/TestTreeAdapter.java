package com.demo.multilevellist.test;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntDef;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.multilevellist.DensityUtil;
import com.demo.multilevellist.R;
import com.demo.multilevellist.TreeUtils;

import java.util.HashMap;
import java.util.List;


/**
 * Created by xulc on 2018/7/27.
 */

public class TestTreeAdapter extends BaseAdapter {
    private Context mcontext;
    private Activity mActivity;
    private List<TestTreePoint> pointList;
    private String keyword = "";

    public List<TestTreePoint> getPointList() {
        return pointList;
    }

    public void setPointList(List<TestTreePoint> pointList) {
        this.pointList = pointList;
    }

    private HashMap<String, TestTreePoint> pointMap = new HashMap<>();

//    private int operateMode = ModeSelect;
    //两种操作模式  点击 或者 选择
//    private static final int ModeClick = 1;
//    private static final int ModeSelect = 2;

//    @IntDef({ModeClick,ModeSelect})
//    public @interface Mode{
//
//    }

    //设置操作模式
//    public void setOperateMode(@Mode int operateMode){
//        this.operateMode = operateMode;
//    }

    public TestTreeAdapter(final Context mcontext, List<TestTreePoint> pointList, HashMap<String, TestTreePoint> pointMap) {
        this.mcontext = mcontext;
        this.mActivity = (Activity) mcontext;
        this.pointList = pointList;
        this.pointMap = pointMap;
    }

    public HashMap<String, TestTreePoint> getPointMap() {
        return pointMap;
    }

    public void setPointMap(HashMap<String, TestTreePoint> pointMap) {
        this.pointMap = pointMap;
    }
    /**
     * 搜索的时候，先关闭所有的条目，然后，按照条件，找到含有关键字的数据
     * 如果是叶子节点，
     */
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//        for (TestTreePoint treePoint : pointList) {
//            treePoint.setExpand(false);
//        }
//        if (!"".equals(keyword)) {
//            for (TestTreePoint tempTreePoint : pointList) {
//                if (tempTreePoint.getNNAME().contains(keyword)) {
//                    if ("0".equals(tempTreePoint.getISLEAF())) {   //非叶子节点
//                        tempTreePoint.setExpand(true);
//                    }
//                    //展开从最顶层到该点的所有节点
//                    openExpand(tempTreePoint);
//                }
//            }
//        }
//        this.notifyDataSetChanged();
//    }

    /**
     * 从TreePoint开始一直展开到顶部
//     * @param treePoint
     */
//    private void openExpand(TestTreePoint treePoint) {
//        if ("0".equals(treePoint.getPARENTID())) {
//            treePoint.setExpand(true);
//        } else {
//            pointMap.get(treePoint.getPARENTID()).setExpand(true);
//            openExpand(pointMap.get(treePoint.getPARENTID()));
//        }
//    }


    //第一要准确计算数量
    @Override
    public int getCount() {
       return pointList.size();
//        int count = 0;
//        for (TestTreePoint tempPoint : pointList) {
//            if ("0".equals(tempPoint.getPARENTID())) {
//                count++;
//            } else {
//                if (getItemIsExpand(tempPoint.getPARENTID())) {
//                    count++;
//                }
//            }
//        }
//        return count;
    }

    //判断当前Id的tempPoint是否展开了
//    private boolean getItemIsExpand(String ID) {
//        for (TestTreePoint tempPoint : pointList) {
//            if (ID.equals(tempPoint.getID())) {
//                return tempPoint.isExpand();
//            }
//        }
//        return false;
//    }

    @Override
    public Object getItem(int position) {
        return pointList.get(position);
    }

//    private int convertPostion(int position) {
//        int count = 0;
//        for (int i = 0; i < pointList.size(); i++) {
//            TestTreePoint treePoint = pointList.get(i);
//            if ("0".equals(treePoint.getPARENTID())) {
//                count++;
//            } else {
//                if (getItemIsExpand(treePoint.getPARENTID())) {
//                    count++;
//                }
//            }
//            if (position == (count - 1)) {
//                return i;
//            }
//        }
//        return 0;
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.adapter_treeview, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.ib_select = (ImageButton) convertView.findViewById(R.id.ib_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TestTreePoint tempPoint = (TestTreePoint) getItem(position);
        int level = TreeUtils.getLevel(tempPoint,pointMap);
        holder.icon.setPadding(25 * level, holder.icon.getPaddingTop(), 0, holder.icon.getPaddingBottom());
//        if ("0".equals(tempPoint.getISLEAF())) {  //如果为父节点
//            if (!tempPoint.isExpand()) {    //不展开显示加号
//                holder.icon.setVisibility(View.VISIBLE);
//                holder.icon.setImageResource(R.drawable.outline_list_collapse);
//            } else {                        //展开显示减号
//                holder.icon.setVisibility(View.VISIBLE);
//                holder.icon.setImageResource(R.drawable.outline_list_expand);
//            }
//        } else {   //如果叶子节点，不占位显示
//            holder.icon.setVisibility(View.INVISIBLE);
//        }
//        if(operateMode == ModeSelect){
//            holder.ib_select.setVisibility(View.VISIBLE);
////            holder.ib_select.setSelected(tempPoint.isSelected());
//            holder.ib_select.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onModeSelect(tempPoint);
//                }
//            });
//        }else{
//            holder.ib_select.setVisibility(View.GONE);
//        }
        //如果存在搜索关键字
        if (keyword != null && !"".equals(keyword) && tempPoint.getNNAME().contains(keyword)) {
            int index = tempPoint.getNNAME().indexOf(keyword);
            int len = keyword.length();
            Spanned temp = Html.fromHtml(tempPoint.getNNAME().substring(0, index)
                    + "<font color=#FF0000>"
                    + tempPoint.getNNAME().substring(index, index + len) + "</font>"
                    + tempPoint.getNNAME().substring(index + len, tempPoint.getNNAME().length()));

            holder.text.setText(temp);
        } else {
            holder.text.setText(tempPoint.getNNAME());
        }
        holder.text.setCompoundDrawablePadding(DensityUtil.dip2px(mcontext, 10));
        return convertView;
    }



//    public void onItemClick(int position) {
//        TestTreePoint treePoint = (TestTreePoint) getItem(position);
//        if ("1".equals(treePoint.getISLEAF())) {   //点击叶子节点
//            //处理回填
//            Toast.makeText(mcontext, getSubmitResult(treePoint), Toast.LENGTH_SHORT).show();
//        } else {  //如果点击的是父类
//            if (treePoint.isExpand()) {
//                for (TestTreePoint tempPoint : pointList) {
//                    if (tempPoint.getPARENTID().equals(treePoint.getID())) {
//                        if ("0".equals(treePoint.getISLEAF())) {
//                            tempPoint.setExpand(false);
//                        }
//                    }
//                }
//                treePoint.setExpand(false);
//            } else {
//                treePoint.setExpand(true);
//            }
//        }
//        this.notifyDataSetChanged();
//    }


    //选择操作
//    private void onModeSelect(TestTreePoint treePoint){
//        if ("1".equals(treePoint.getISLEAF())) {   //选择叶子节点
//            //处理回填
//            treePoint.setSelected(!treePoint.isSelected());
//        } else {                                   //选择父节点
//            int position = pointList.indexOf(treePoint);
//            boolean isSelect = treePoint.isSelected();
//            treePoint.setSelected(!isSelect);
//            if(position == -1){
//                return ;
//            }
//            if(position == pointList.size()-1){
//                return;
//            }
//            position++;
//            for(;position < pointList.size();position++){
//                TestTreePoint tempPoint =  pointList.get(position);
//                if(tempPoint.getPARENTID().equals(treePoint.getPARENTID())){    //如果找到和自己同级的数据就返回
//                    break;
//                }
//                tempPoint.setSelected(!isSelect);
//            }
//        }
//        this.notifyDataSetChanged();
//    }

    //选中所有的point
//    private void selectPoint(TestTreePoint treePoint) {
//        if(){
//
//        }
//    }







    class ViewHolder {
        TextView text;
        ImageView icon;
        ImageButton ib_select;
    }

}
