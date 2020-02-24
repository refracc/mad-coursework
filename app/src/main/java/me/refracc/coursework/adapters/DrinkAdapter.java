package me.refracc.coursework.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

import me.refracc.coursework.R;
import me.refracc.coursework.info.Drink;

@SuppressWarnings({"deprecated", "unused"})
public class DrinkAdapter extends ArrayAdapter<Drink> {

    private Context ctxt;
    private int mResource;
    private int lastPosition = -1;

    
    private static class ViewHolder {
        TextView name;
        TextView abv;
        TextView volume;
        ImageView image;
    }

    public DrinkAdapter(Context context, int resource, List<Drink> objects) {
        super(context, resource, objects);
        ctxt = context;
        mResource = resource;
    }
    
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        //sets up the image loader library
        setupImageLoader();

        //get the persons information
        String name = getItem(position).getName();
        String abv = getItem(position).getAbv();
        String volume = getItem(position).getVol();
        String imgURL = getItem(position).getResURL();

        //create the view anim_result for showing the animation
        final View anim_result;

        //ViewHolder object
        ViewHolder viewHolder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(ctxt);
            convertView = inflater.inflate(mResource, parent, false);
            viewHolder= new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.name_text);
            viewHolder.abv = convertView.findViewById(R.id.abv_text);
            viewHolder.volume = convertView.findViewById(R.id.volume_text);
            viewHolder.image = convertView.findViewById(R.id.image);

            anim_result = convertView;

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            anim_result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(ctxt, (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim); // Depends on scroll direction will modify the
        anim_result.startAnimation(animation);
        lastPosition = position;

        viewHolder.name.setText(name);
        viewHolder.abv.setText(abv);
        viewHolder.volume.setText(volume);

        //create the im object
        ImageLoader im = ImageLoader.getInstance();

        int errorImage = ctxt.getResources().getIdentifier("@drawable/error",null, ctxt.getPackageName());

        //create display options
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading(true).showImageForEmptyUri(errorImage).showImageOnFail(errorImage).showImageOnLoading(errorImage).build();

        //download and display image from url
        im.displayImage(imgURL, viewHolder.image, options);

        return convertView;
    }

    /**
     * Required for setting up the Universal Image loader Library
     */
    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctxt).defaultDisplayImageOptions(defaultOptions).memoryCache(new WeakMemoryCache()).discCacheSize(100 * 1024 * 1024).build();
        ImageLoader.getInstance().init(config);
    }

}
