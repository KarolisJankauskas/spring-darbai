import { AddAdForm } from "./AddAdForm";
import { useAuth } from "../../context/AuthContext";

export const AddAdPage = () => {
    const { user } = useAuth();

    if (!user?.roles?.includes("ROLE_ADMIN","ROLE_USER")) {
        return (
            <div className="text-center text-red-500 py-8">
                You do not have permission to access this page.
            </div>
        );
    }

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6 text-gray-800">Add a New Ad</h1>
            <AddAdForm />
        </div>
    );
};