package com.OOGraph.raster;

import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.Vertex;
import com.OOGraph.raster.shaders.PixelShader;
import com.OOGraph.raster.surfaces.Surface;
import com.OOGraph.scenegraph.MeshRenderer;

public class RasterMeshRenderer<TVertex extends Vertex, TPixel> implements MeshRenderer<TVertex> {
    private Surface<TPixel> drawingSurface;
    private VertexSurfaceDrawer<TPixel, TVertex> drawer;
    private PixelShader<TPixel, TVertex> pixelShader;
    private ZBuffer zBuffer;

    public RasterMeshRenderer(Surface<TPixel> drawingSurface, PixelShader<TPixel, TVertex> pixelShader, ZBuffer zBuffer) {
        this.drawingSurface = drawingSurface;
        this.pixelShader = pixelShader;
        this.drawer = new VertexSurfaceDrawer<>(this.drawingSurface, this.pixelShader);
        this.zBuffer = zBuffer;
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

    public VertexSurfaceDrawer<TPixel, TVertex> getDrawer() {
        return drawer;
    }

    public ZBuffer getzBuffer() {
        return zBuffer;
    }

    @Override
    public void drawMesh(Mesh<TVertex> mesh) {
        drawer.drawMesh(mesh, zBuffer);
    }
}
