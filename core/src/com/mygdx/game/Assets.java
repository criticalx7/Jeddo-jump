package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import java.io.File;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import static com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

public class Assets {
    public static final AssetManager manager = new AssetManager();
    public static final AssetDescriptor<Texture> BG01 = new AssetDescriptor<>("BG01.png", Texture.class);

    public static final AssetDescriptor<Texture> P001 = new AssetDescriptor<>("P001.png", Texture.class);
    public static final AssetDescriptor<Texture> P002 = new AssetDescriptor<>("P002.png", Texture.class);

    public static final AssetDescriptor<Texture> T001 = new AssetDescriptor<>("T001.png", Texture.class);
    public static final AssetDescriptor<Texture> T002 = new AssetDescriptor<>("T002.png", Texture.class);
    public static final AssetDescriptor<Texture> T003 = new AssetDescriptor<>("T003.png", Texture.class);
    public static final AssetDescriptor<Texture> T004 = new AssetDescriptor<>("T004.png", Texture.class);
    public static final AssetDescriptor<Texture> T005 = new AssetDescriptor<>("T005.png", Texture.class);
    public static final AssetDescriptor<Texture> T006 = new AssetDescriptor<>("T006.png", Texture.class);
    public static final AssetDescriptor<Texture> T007 = new AssetDescriptor<>("T007.png", Texture.class);


    public static final AssetDescriptor<Texture> E001 = new AssetDescriptor<>("E001.png", Texture.class);
    public static final AssetDescriptor<Texture> E002 = new AssetDescriptor<>("E002.png", Texture.class);

    public static final AssetDescriptor<Texture> C001 = new AssetDescriptor<>("C001.png", Texture.class);
    public static final AssetDescriptor<Texture> C002 = new AssetDescriptor<>("C002.png", Texture.class);

    public static final AssetDescriptor<Texture> I001 = new AssetDescriptor<>("I001.png", Texture.class);

    public static final String F001 = "F001.ttf";


    void load() {
        loadTexture();
        loadFont();
    }

    void loadTexture() {
        manager.load(BG01);
        manager.load(P001);
        manager.load(P002);
        manager.load(T001);
        manager.load(T002);
        manager.load(T003);
        manager.load(T004);
        manager.load(T005);
        manager.load(T006);
        manager.load(T007);
        manager.load(C001);
        manager.load(C002);
        manager.load(E001);
        manager.load(E002);
        manager.load(I001);
    }

    void loadFont() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreeTypeFontLoaderParameter font = new FreeTypeFontLoaderParameter();
        FreeTypeFontParameter fontParam = new FreeTypeFontParameter();
        fontParam.borderColor = Color.BLACK;
        fontParam.borderWidth = 1;
        fontParam.size = 25;
        font.fontFileName = F001;
        font.fontParameters = fontParam;
        manager.load("F001.ttf", BitmapFont.class, font);;

    }

    void await() {
        manager.finishLoading();
    }


    void dispose() {
        manager.dispose();
    }
}
