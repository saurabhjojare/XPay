import React from "react";
import PageLayout from "../common/page.layout";
import { Box } from "@mui/material";

const HomePage: React.FC = () => {
    return (
        <>
            <PageLayout>
                <Box display={"flex"} justifyContent={"center"}
                    alignItems={"center"} height={"100vh"}>
                    <h2>Welcome Home</h2>
                </Box>
            </PageLayout>
        </>
    );
};
export default HomePage;
