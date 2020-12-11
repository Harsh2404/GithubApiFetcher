package com.example.githubapifetcher.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.githubapifetcher.R;
import com.example.githubapifetcher.viewModel.ViewModel;
import com.example.githubapifetcher.model.Owner;

public class FragmentHome extends Fragment {

    private EditText mnickname;
    private Button mselect;
    private String BASE_URL="https://api.github.com";
    private ViewModel mviewModel;
    private Owner mowner=null;

    public FragmentHome(){
//        super(R.layout.fragment_home);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        EditText mnickname=(EditText)view.findViewById(R.id.nickname_text);
        Button mselect=(Button)view.findViewById(R.id.select_button);

        NavController navController= Navigation.findNavController(view);

        mviewModel=new ViewModelProvider
                .AndroidViewModelFactory(getActivity().getApplication())
                .create(ViewModel.class);


        mselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname=mnickname.getText().toString();

                if(nickname.isEmpty()){
                    Toast.makeText(getContext(),"nickname can't be null:Enter valid nickname",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    Bundle bundle = new Bundle();
                    bundle.putString("nickname", nickname);
                    navController.navigate(R.id.action_fragmentHome_to_fragmentRepolist2,bundle);
                }

            }

        });

    }

}
