package com.example.githubapifetcher.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapifetcher.R;
import com.example.githubapifetcher.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends   RecyclerView.Adapter<RepoAdapter.MyViewHolder> {
    private List<Repo> mrepos=new ArrayList<Repo>();
    private Context mcontext;


    public RepoAdapter(Context context){
        mcontext=context;
    }
    public void setRepos(List<Repo> repos){
        mrepos=repos;
    }

    @NonNull
    @Override
    public RepoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.MyViewHolder holder, int position) {

        Repo currentrepo=new Repo();
        currentrepo=mrepos.get(position);

        TextView repo_Name=holder.repoName;
        repo_Name.setText(currentrepo.getFullName());

        TextView repo_desc=holder.repoDescription;
        repo_desc.setText(currentrepo.getDescription());


    }

    @Override
    public int getItemCount() {
        return mrepos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView repoName,repoDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            repoName=itemView.findViewById(R.id.repo_name);
            repoDescription=itemView.findViewById(R.id.repo_description);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Repo repo=mrepos.get(getAdapterPosition());
            Bundle bundle=new Bundle();
            bundle.putString("htmlurl", repo.getHtmlUrl());

            NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_fragmentRepolist_to_fragmentRepoDescription,bundle);
        }
    }


}
