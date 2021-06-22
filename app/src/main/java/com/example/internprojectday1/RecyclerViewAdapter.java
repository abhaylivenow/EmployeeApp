package com.example.internprojectday1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EmployeeHolder>{

    private Context mContext;
    private List<Employee> mEmployeeList;
    private onClickListener mOnClickListener;

    public RecyclerViewAdapter(Context context, List<Employee> employeeList, onClickListener onClickListener){
        mContext = context;
        mEmployeeList = employeeList;
        this.mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new EmployeeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.EmployeeHolder holder, int position) {

        Employee currentEmployee = mEmployeeList.get(position);

        holder.txt_name.setText(currentEmployee.getName());
        holder.txt_email.setText(currentEmployee.getEmail());
        holder.txt_id.setText(currentEmployee.getId());
        holder.txt_mobile.setText(currentEmployee.getMobile());

        holder.txt_designation.setText(currentEmployee.getDesignation());
        holder.txt_reporting_to.setText(currentEmployee.getReportingTo());
        holder.txt_doj.setText(currentEmployee.getDOJ());
        holder.txt_rights.setText(currentEmployee.getRights());

    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    class EmployeeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_name, txt_id, txt_email, txt_mobile, txt_designation, txt_reporting_to, txt_doj, txt_rights;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_email = itemView.findViewById(R.id.txtEmail);
            txt_mobile = itemView.findViewById(R.id.txt_mobile);

            txt_designation = itemView.findViewById(R.id.txt_designation);
            txt_reporting_to = itemView.findViewById(R.id.txt_reporting_to);
            txt_doj = itemView.findViewById(R.id.txt_doj);
            txt_rights = itemView.findViewById(R.id.txt_rights);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onProductCLick(getAdapterPosition());
        }
    }

    public interface onClickListener{
        void onProductCLick(int position);
    }
}
