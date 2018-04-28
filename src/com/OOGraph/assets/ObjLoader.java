package com.OOGraph.assets;

import com.OOGraph.math.Vector;
import com.OOGraph.primitives.meshes.ArrayMesh;
import com.OOGraph.primitives.meshes.Mesh;
import com.OOGraph.primitives.vertices.TexturedNormalVertex;
import com.OOGraph.primitives.vertices.Triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ObjLoader implements AssetLoader<Mesh<TexturedNormalVertex>> {
    private static ObjLoader _instance = null;

    private ObjLoader() {
    }

    public static ObjLoader getInstance() {
        if (_instance == null) _instance = new ObjLoader();
        return _instance;
    }

    @Override
    public Mesh<TexturedNormalVertex> loadAsset(InputStream stream) throws IOException, InvalidMeshException {
        Mesh<TexturedNormalVertex> mesh = new ArrayMesh<>();
        List<Vector> vertices = new ArrayList<>();
        List<Vector> normals = new ArrayList<>();
        List<Vector> uvs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        int linen = 0;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    String[] split = line.substring(2).trim().split(" ");
                    vertices.add(new Vector(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2])));
                } else if (line.startsWith("vn ")) {
                    String[] split = line.substring(2).trim().split(" ");
                    normals.add(new Vector(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2])).multiply(new Vector(1, -1, 1)));
                } else if (line.startsWith("vt ")) {
                    String[] split = line.substring(2).trim().split(" ");
                    uvs.add(new Vector(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2])));
                } else if (line.startsWith("f ")) {
                    String[] split = line.substring(2).trim().split(" ");
                    TexturedNormalVertex[] vs = new TexturedNormalVertex[3];
                    for (int i = 0; i < 3; i++) {
                        String[] splitd = split[i].split("/");
                        int vindex = Integer.parseInt(splitd[0]) - 1;
                        int tindex = Integer.parseInt(splitd[1]) - 1;
                        int nindex = Integer.parseInt(splitd[2]) - 1;
                        vs[i] = new TexturedNormalVertex(vertices.get(vindex), normals.get(nindex), uvs.get(tindex));
                    }
                    mesh.add(new Triangle<>(vs[0], vs[1], vs[2]));
                    if (split.length == 4) {
                        TexturedNormalVertex[] vs2 = new TexturedNormalVertex[3];
                        String[] splitd = split[0].split("/");
                        int vindex = Integer.parseInt(splitd[0]) - 1;
                        int tindex = Integer.parseInt(splitd[1]) - 1;
                        int nindex = Integer.parseInt(splitd[2]) - 1;
                        vs2[0] = new TexturedNormalVertex(vertices.get(vindex), normals.get(nindex), uvs.get(tindex));
                        splitd = split[2].split("/");
                        vindex = Integer.parseInt(splitd[0]) - 1;
                        tindex = Integer.parseInt(splitd[1]) - 1;
                        nindex = Integer.parseInt(splitd[2]) - 1;
                        vs2[1] = new TexturedNormalVertex(vertices.get(vindex), normals.get(nindex), uvs.get(tindex));
                        splitd = split[3].split("/");
                        vindex = Integer.parseInt(splitd[0]) - 1;
                        tindex = Integer.parseInt(splitd[1]) - 1;
                        nindex = Integer.parseInt(splitd[2]) - 1;
                        vs2[2] = new TexturedNormalVertex(vertices.get(vindex), normals.get(nindex), uvs.get(tindex));
                        mesh.add(new Triangle<>(vs2[0], vs2[1], vs2[2]));
                    }
                }
                linen++;
            }
        } catch (Exception e) {
            throw new InvalidMeshException(linen);
        }
        return mesh;
    }
}

class InvalidMeshException extends IOException {
    private int line;

    public InvalidMeshException(int line) {
        super("Invalid mesh in line: " + line);
        this.line = line;
    }

    public int getLine() {
        return line;
    }
}
