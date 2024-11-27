package com.example.a1stexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RoomInfoAdapter extends BaseAdapter {
    private Context context;
    private List<Student> students;

    public RoomInfoAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.room_info_item, parent, false);
        }

        TextView tvUsername = convertView.findViewById(R.id.tvUsername);
        TextView tvRoomNumber = convertView.findViewById(R.id.tvRoomNumber);
        TextView tvBranch = convertView.findViewById(R.id.tvBranch);
        TextView tvYear = convertView.findViewById(R.id.tvYear);

        Student student = students.get(position);

        tvUsername.setText(student.getUsername());
        tvRoomNumber.setText(student.getRoomNumber());
        tvBranch.setText(student.getBranch());
        tvYear.setText(student.getYear());

        return convertView;
    }
}
