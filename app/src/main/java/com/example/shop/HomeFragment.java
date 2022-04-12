package com.example.shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private ViewPager bannerSlider;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;

    private TextView horizontalLayoutTitle;
    private Button horizontalViewAllBtn;
    private RecyclerView horizontalRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link", "Cat"));
        categoryModelList.add(new CategoryModel("link", "Dog"));
        categoryModelList.add(new CategoryModel("link", "Tiger"));
        categoryModelList.add(new CategoryModel("link", "Elephant"));
        categoryModelList.add(new CategoryModel("link", "Leon"));
        categoryModelList.add(new CategoryModel("link", "Fish"));
        categoryModelList.add(new CategoryModel("link", "Crocodile"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        bannerSlider = view.findViewById(R.id.banner_view_page);

        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.slider));
        sliderModelList.add(new SliderModel(R.mipmap.slider));
        sliderModelList.add(new SliderModel(R.mipmap.slider));
        sliderModelList.add(new SliderModel(R.mipmap.slider));
        sliderModelList.add(new SliderModel(R.mipmap.logo));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSlider.setClipToPadding(false);
        bannerSlider.setPageMargin(20);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    pageLoop();
                }
            }
        };
        bannerSlider.setAdapter(sliderAdapter);
        bannerSlider.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSlider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLoop();
                stopBannerSlider();
                if (event.getAction() == MotionEvent.ACTION_UP)
                    startBannerSlideShow();
                return false;
            }
        });

        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_title);
        horizontalViewAllBtn = view.findViewById(R.id.horizontal_scroll_btn);
        horizontalRecycler = view.findViewById(R.id.horizontal_scroll_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item, "NoteBook", "700 $"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item_1, "Personal Computer", "200 $"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item_1, "Phone", "1700 $"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.product_item, "Watch", "7300 $"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecycler.setLayoutManager(linearLayoutManager);
        horizontalRecycler.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutAllBtn = view.findViewById(R.id.grid_product_layout_view_all_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("QQQQ", horizontalProductScrollModelList.get(position).getProductTitle());
//                Intent productDetailIntent = new Intent(view.getContext(), ProductDetailActivity.class);
//                view.getContext().startActivity(productDetailIntent);
//            }
//        });

        return view;
    }

    private void pageLoop(){
        if (currentPage == sliderModelList.size() - 2){
            currentPage = 2;
            bannerSlider.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1){
            currentPage = sliderModelList.size() - 3;
            bannerSlider.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow(){
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()) {
                    currentPage = 1;
                }
                bannerSlider.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);
    }

    private void stopBannerSlider(){
        timer.cancel();
    }
}