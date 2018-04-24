package com.OOGraph.rastersurfaces;

import com.OOGraph.primitives.Mesh;
import com.OOGraph.primitives.Vertex;
import com.OOGraph.rastersurfaces.shaders.PixelShader;
import com.OOGraph.scenegraph.Renderer;

public class RasterRenderer<TVertex extends Vertex, TPixel> implements Renderer<TVertex> {
    protected Surface<TPixel> drawingSurface;
    protected SurfaceDrawer<TPixel, TVertex> drawer;
    protected PixelShader<TPixel, TVertex> pixelShader;

    public RasterRenderer(Surface<TPixel> drawingSurface, PixelShader<TPixel, TVertex> pixelShader) {
        this.drawingSurface = drawingSurface;
        this.pixelShader = pixelShader;
        this.drawer = new SurfaceDrawer<>(this.drawingSurface, this.pixelShader);
    }

    public PixelShader<TPixel, TVertex> getPixelShader() {
        return pixelShader;
    }

    public void setPixelShader(PixelShader<TPixel, TVertex> pixelShader) {
        this.pixelShader = pixelShader;
    }

    public Surface<TPixel> getDrawingSurface() {
        return drawingSurface;
    }

    public SurfaceDrawer<TPixel, TVertex> getDrawer() {
        return drawer;
    }

    @Override
    public void drawMesh(Mesh<TVertex> mesh) {
        drawer.drawMesh(mesh);
    }

    @Override
    public void fill() {
        drawer.fill();
    }
}
